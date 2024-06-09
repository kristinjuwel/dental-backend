package com.dental.records.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dental.records.model.Consent;

public interface ConsentRepository extends JpaRepository<Consent, Long> {
	Consent findByConsentId(Long consentId);
	List<Consent> findByExamId(Long examId);
	List<Consent> findByPatientName(String patientName);
	List<Consent> findByCompletionStatus(String completionStatus);
}
