package com.dental.records.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dental.records.model.UnverifiedPatient;

public interface UnverifiedPatientRepository extends JpaRepository<UnverifiedPatient, Long>{
	UnverifiedPatient findByPatientId(Long patientId);
}
