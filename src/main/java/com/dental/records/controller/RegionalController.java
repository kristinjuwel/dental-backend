package com.dental.records.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dental.records.model.Regional;
import com.dental.records.service.RegionalService;

@RestController
@CrossOrigin(origins = "https://dental-health-record.vercel.app")
@RequestMapping("/regional")
public class RegionalController {
	private final RegionalService regionalService;

	@Autowired
	public RegionalController(RegionalService regionalService) {
		super();
		this.regionalService = regionalService;
	}
	
	@GetMapping("/{regionName}/{reportMonth}/{reportYear}")
    public Regional getRegionalsByRegionNameAndReportMonthAndReportYear(
    		@PathVariable String regionName,
    		@PathVariable String reportMonth,
    		@PathVariable String reportYear) {
        return regionalService.getRegionalsByRegionNameAndReportMonthAndReportYear(regionName, reportMonth, reportYear);
    }
	
	@PostMapping
	public Regional manualAdd(@RequestPart("regional") Regional regional, @RequestPart("staffSign") MultipartFile staffSign, @RequestPart("chiefSign") MultipartFile chiefSign) throws IOException {
		return regionalService.save(regional, staffSign, chiefSign);
	}
	
	
}