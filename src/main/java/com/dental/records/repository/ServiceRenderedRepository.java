package com.dental.records.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dental.records.model.ServiceRendered;

public interface ServiceRenderedRepository extends JpaRepository<ServiceRendered, Long>{
	List<ServiceRendered> findAll();
	ServiceRendered findByServiceId(Long serviceId);
	List<ServiceRendered> findByExamId(Long examId);
}
