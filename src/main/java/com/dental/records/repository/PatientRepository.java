package com.dental.records.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dental.records.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
	List<Patient> findAll(); 
	Patient findByPatientId(Long patientId);

}
