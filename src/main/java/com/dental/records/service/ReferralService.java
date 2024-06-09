package com.dental.records.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dental.records.model.DentalExam;
import com.dental.records.model.Dentist;
import com.dental.records.model.Referral;
import com.dental.records.model.ServiceRendered;
import com.dental.records.model.User;
import com.dental.records.repository.DentalExamRepository;
import com.dental.records.repository.DentistRepository;
import com.dental.records.repository.ReferralRepository;
import com.dental.records.repository.ServiceRenderedRepository;
import com.dental.records.repository.UserRepository;

@Service
public class ReferralService {
	private final ReferralRepository referralRepository;
	private final DentistRepository dentistRepository;
	private final DentalExamRepository dentalExamRepository;
	private final UserRepository userRepository;
	private final ServiceRenderedRepository renderedRepository;
	
	@Autowired
	public ReferralService(ReferralRepository referralRepository, DentistRepository dentistRepository,
			DentalExamRepository dentalExamRepository, UserRepository userRepository, ServiceRenderedRepository renderedRepository) {
		super();
		this.referralRepository = referralRepository;
		this.dentistRepository = dentistRepository;
		this.dentalExamRepository = dentalExamRepository;
		this.userRepository = userRepository;
		this.renderedRepository = renderedRepository;
	}
	
    private LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
	
    public int calculateAge(Date birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("The birthDate cannot be null");
        }

        LocalDate birthLocalDate = convertToLocalDate(birthDate);
        LocalDate now = LocalDate.now();

        return Period.between(birthLocalDate, now).getYears();
    }
    
	public void createReferral(Long examId, Long dentistId) {
		List<ServiceRendered> services = renderedRepository.findByExamId(examId);
		DentalExam record = dentalExamRepository.findByExamId(examId);
		Dentist dentist = dentistRepository.findByDentistId(dentistId);
		User user = userRepository.findByUserId(dentistId);
		Referral referral = new Referral();
		referral.setDentistId(dentistId);
		referral.setExamId(examId);
		referral.setPatientName(record.getFirstName()+" "+record.getMiddleName()+ " "+ record.getLastName());
		referral.setPatientAge(calculateAge(record.getBirthday()));
		referral.setPatientAddress(record.getAddress());
		referral.setCurrentDate(record.getPurposeDate());
		referral.setPurpose(record.getPurpose());
		referral.setDentistName(user.getFirstName()+" "+user.getMiddleName()+ " "+ user.getLastName());
		referral.setDentistLicense(dentist.getLicenseNo());
		String allTreatments = services.stream()
                .map(ServiceRendered::getTreatment)
                .collect(Collectors.joining(", "));

		referral.setTreatment(allTreatments);
		if (dentist.getClinic() != null && dentist.getClinicLoc() != null) {
	        referral.setFromDent(dentist.getClinic() + ", " + dentist.getClinicLoc());
	    }
		// Save the referral to the repository (assuming you have a referralRepository)
		referralRepository.save(referral);
	}
	
	public void addExtra(Referral referral, MultipartFile imageLeft, MultipartFile imageRight, Long referralId) throws IOException {
	    byte[] imageLeftBytes = imageLeft.getBytes();
	    byte[] imageRightBytes = imageRight.getBytes();
	    
	    // Set the byte arrays to the referral
	    referral.setDrawingOne(imageLeftBytes);
	    referral.setDrawingTwo(imageRightBytes);

	    // Find the existing referral by ID
	    Referral existingReferral = referralRepository.findByReferralId(referralId);
	                                                 

	    // Update the existing referral with new images
	    existingReferral.setDrawingOne(imageLeftBytes);
	    existingReferral.setDrawingTwo(imageRightBytes);
	    existingReferral.setCompletionStatus("Completed");
	    // Save the updated referral to the repository
	    referralRepository.save(existingReferral);
	}
	
	public Referral findByReferralId(Long referralId) {
		return referralRepository.findByReferralId(referralId);
	}
	
	public List<Referral> findByPatient(String patientName){
		return referralRepository.findByPatientName(patientName);
	}
	public List<Referral> findByDentist(String dentistName){
		return referralRepository.findByDentistName(dentistName);
	}
	public List<Referral> findByStatus(String completionStatus){
		return referralRepository.findByCompletionStatus(completionStatus);
	}
    	
	
	
}
