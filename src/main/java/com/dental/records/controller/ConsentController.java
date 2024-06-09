package com.dental.records.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dental.records.model.Consent;
import com.dental.records.service.ConsentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/consent")
public class ConsentController {
	private final ConsentService consentService;
	
	@Autowired
	public ConsentController(ConsentService consentService) {
		super();
		this.consentService = consentService;
	}
	
	@GetMapping("/byExamId/{examId}")
    public List<Consent> findByExamId(@PathVariable Long examId) {
        return consentService.findByExamId(examId);
    }

    @GetMapping("/byPatientName/{patientName}")
    public List<Consent> findByPatientName(@PathVariable String patientName) {
        return consentService.findByPatientName(patientName);
    }
    
    @GetMapping("/byStatus/{completionStatus}")
    public List<Consent> findByCompletionStatus(@PathVariable String completionStatus) {
        return consentService.findByStatus(completionStatus);
    }

    @PostMapping
    public String AddConsentDetails(@RequestBody String treatment, Date treatDate, Long examId) {
        consentService.addToConsent(treatment, treatDate, examId);
        return "Successful";
    }

    @PostMapping("/addSignatories/{consentId}")
    public String addSignatories(@RequestParam MultipartFile patSign, @RequestParam MultipartFile dentSign, @PathVariable Long consentId) throws IOException {
        consentService.addSignatories(patSign, dentSign, consentId);
        return "Signatories added successfully";
    }
}






