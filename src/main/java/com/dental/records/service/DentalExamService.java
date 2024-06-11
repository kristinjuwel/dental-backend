package com.dental.records.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dental.records.model.DentalExam;
import com.dental.records.repository.DentalExamRepository;

@Service
public class DentalExamService {
	private final DentalExamRepository dentalExamRepository;

	@Autowired
	public DentalExamService(DentalExamRepository dentalExamRepository) {
		this.dentalExamRepository = dentalExamRepository;
	}
	
    public List<DentalExam> findAll() {
        return dentalExamRepository.findAll();
    }

    public Optional<DentalExam> findById(Long examId) {
        return dentalExamRepository.findById(examId);
    }

    public DentalExam save(DentalExam dentalExam, MultipartFile patImage, MultipartFile dentalImage, MultipartFile patSign) throws IOException {
    	byte[] patImageBytes = patImage.getBytes();
    	byte[] dentalImageBytes = dentalImage.getBytes();
    	byte[] patSignBytes = patSign.getBytes();
    	dentalExam.setPicture(patImageBytes);
    	dentalExam.setPatSign(patSignBytes);
    	dentalExam.setDentImage(dentalImageBytes);
    	return dentalExamRepository.save(dentalExam);
    }
    public void updateChiefSign(Long examId, String chiefName, MultipartFile chief) throws IOException {
    	byte[] chiefBytes = chief.getBytes();
    	DentalExam unsigned = dentalExamRepository.findByExamId(examId);
    	unsigned.setChiefName(chiefName);
    	unsigned.setChiefSign(chiefBytes);
    	unsigned.setCompletionStatus("Completed");
    	dentalExamRepository.save(unsigned);
    	
    }
    public DentalExam update(Long examId, DentalExam updatedExam) {
        Optional<DentalExam> optionalExam = dentalExamRepository.findById(examId);
        if (optionalExam.isPresent()) {
            DentalExam existingExam = optionalExam.get();
            existingExam.setRank(updatedExam.getRank());
            existingExam.setFirstName(updatedExam.getFirstName());
            existingExam.setMiddleName(updatedExam.getMiddleName());
            existingExam.setLastName(updatedExam.getLastName());
            existingExam.setBirthday(updatedExam.getBirthday());
            existingExam.setUnitAssign(updatedExam.getUnitAssign());
            existingExam.setSponsor(updatedExam.getSponsor());
            existingExam.setAddress(updatedExam.getAddress());
            existingExam.setContactNumber(updatedExam.getContactNumber());
            existingExam.setRegion(updatedExam.getRegion());
            existingExam.setPurpose(updatedExam.getPurpose());
            existingExam.setPurposeDate(updatedExam.getPurposeDate());
            existingExam.setPromotionDate(updatedExam.getPromotionDate());
            existingExam.setTrainingDate(updatedExam.getTrainingDate());
            existingExam.setCalculus(updatedExam.getCalculus());
            existingExam.setRemarks(updatedExam.getRemarks());
            existingExam.setComplaint(updatedExam.getComplaint());
            existingExam.setMedHist(updatedExam.getMedHist());
            existingExam.setBp(updatedExam.getBp());
            existingExam.setChiefName(updatedExam.getChiefName());
            existingExam.setCompletionStatus(updatedExam.getCompletionStatus());
            return dentalExamRepository.save(existingExam);
        } else {
            return null;
        }
    }

    public void deleteById(Long examId) {
        dentalExamRepository.deleteById(examId);
    }

    public List<DentalExam> findByDentistId(Long dentistId) {
        return dentalExamRepository.findByDentistId(dentistId);
    }

    public List<DentalExam> findByFullName(String firstName, String lastName) {
        return dentalExamRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<DentalExam> findByCompletionStatus(String completionStatus) {
        return dentalExamRepository.findByCompletionStatus(completionStatus);
    }
}