package com.dental.records.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dental.records.model.Regional;

public interface RegionalRepository extends JpaRepository<Regional, Long>{
	Regional findByRegionalId(Long regionalId);
	List<Regional> findByRegionName(String regionName);
	List<Regional> findByReportMonthAndReportYear(String reportMonth, String reportYear);
}
