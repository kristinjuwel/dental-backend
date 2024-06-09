package com.dental.records.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dental.records.model.Referral;

public interface ReferralRepository extends JpaRepository<Referral, Long> {
	Referral findByReferralId(Long referralId);
	List<Referral> findByPatientName(String patientName);
	List<Referral> findByDentistName(String dentistName);
	List<Referral> findByCompletionStatus(String completionStatus);
}
