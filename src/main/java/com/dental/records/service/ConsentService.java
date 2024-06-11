package com.dental.records.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dental.records.model.Consent;
import com.dental.records.model.DentalExam;
import com.dental.records.repository.ConsentRepository;
import com.dental.records.repository.DentalExamRepository;

@Service
public class ConsentService {
	private final ConsentRepository consentRepository;
	private final DentalExamRepository dentalExamRepository;
	
	@Autowired
	public ConsentService(ConsentRepository consentRepository, DentalExamRepository dentalExamRepository) {
		super();
		this.consentRepository = consentRepository;
		this.dentalExamRepository = dentalExamRepository;
	}
	
	private LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toLocalDate(); // Direct conversion from java.sql.Date to java.time.LocalDate
    }

	
    public int calculateAge(Date birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("The birthDate cannot be null");
        }

        LocalDate birthLocalDate = convertToLocalDate(birthDate);
        LocalDate now = LocalDate.now();

        return Period.between(birthLocalDate, now).getYears();
    }
    
	public Consent addToConsent(String treatment, Date treatDate, Long examId) {
		DentalExam record = dentalExamRepository.findByExamId(examId);
        Consent consent = new Consent();
		consent.setExamId(examId);
		consent.setPatientName(record.getFirstName()+" "+record.getMiddleName()+" "+record.getLastName());
		consent.setPatientAge(calculateAge(record.getBirthday()));
		consent.setTreatment(treatment);
		consent.setTreatDate(treatDate);

		consent.setCompletionStatus("Completed");
		return consentRepository.save(consent);
	}
	
	public void addSignatories(MultipartFile patSign, MultipartFile dentSign, Long consentId) throws IOException {
	    // Retrieve the consent by its ID
	    Consent unsigned = consentRepository.findByConsentId(consentId);
	    
	    // Convert MultipartFile to byte arrays
	    byte[] patSignBytes = patSign.getBytes();
	    byte[] dentSignBytes = dentSign.getBytes();
	    
	    // Update the signatures in the consent entity
	    unsigned.setPatientSign(patSignBytes);
	    unsigned.setDentistSign(dentSignBytes);
	    
	    // Save the updated consent entity
	    consentRepository.save(unsigned);
	}

	
	public List<Consent> findByExamId(Long examId) {
		return consentRepository.findByExamId(examId);
	}
	
	public List<Consent> findByPatientName(String patientName) {
		return consentRepository.findByPatientName(patientName);
	}
	
	public List<Consent> findByStatus(String completionStat) {
		return consentRepository.findByCompletionStatus(completionStat);
	}
	
	
	
	
	
	//ServiceRendered rendered = renderedRepository.findByExamId(examId);
	
	
	
}
