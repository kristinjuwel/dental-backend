package com.dental.records.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dental.records.model.Consent;
import com.dental.records.service.ConsentService;

@RestController
@CrossOrigin(origins = "https://dental-health-record.vercel.app")
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
    
    @CrossOrigin(origins = "https://dental-health-record.vercel.app")
    @PostMapping("/{treatDate}/{examId}")
    public Consent AddConsentDetails(@RequestBody String treatment, @PathVariable Date treatDate, @PathVariable Long examId) {
        return consentService.addToConsent(treatment, treatDate, examId);
    }


    @PutMapping("/addSignatories/{consentId}")
    public String addSignatories(@RequestPart("patSign") MultipartFile patSign, @RequestPart("dentSign") MultipartFile dentSign, @PathVariable Long consentId) throws IOException {
        consentService.addSignatories(patSign, dentSign, consentId);
        return "Signatories added successfully";
    }
}






