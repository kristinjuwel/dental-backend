package com.dental.records.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dental.records.model.UnverifiedDentist;

public interface UnverifiedDentistRepository extends JpaRepository<UnverifiedDentist, Long>{
	UnverifiedDentist findByDentistId(Long dentistId);
}
