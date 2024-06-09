package com.dental.records.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dental.records.model.Dentist;

public interface DentistRepository extends JpaRepository<Dentist, Long>{
	List<Dentist> findAll(); 
	Dentist findByDentistId(Long dentistId);
}
