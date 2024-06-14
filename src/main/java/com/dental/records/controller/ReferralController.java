package com.dental.records.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dental.records.model.Referral;
import com.dental.records.service.ReferralService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/referral")
public class ReferralController {
	private final ReferralService referralService;
	
	@Autowired
	public ReferralController(ReferralService referralService) {
		super();
		this.referralService = referralService;
	}
	
	@GetMapping("/{dentistId}/{examId}")
	    public Referral getReferralByDentistIdAndExamId(
	    		@PathVariable Long dentistId,
	    		@PathVariable Long examId) {
	        return referralService.findByDentistIdAndExamId(dentistId, examId);
	}
	
    @GetMapping("/{referralId}")
    public ResponseEntity<Referral> getReferralById(@PathVariable Long referralId) {
        Referral referral = referralService.findByReferralId(referralId);
        return ResponseEntity.ok(referral);
    }

    @GetMapping("/patient")
    public ResponseEntity<List<Referral>> getReferralsByPatient(@RequestParam String patientName) {
        List<Referral> referrals = referralService.findByPatient(patientName);
        return ResponseEntity.ok(referrals);
    }

    @GetMapping("/dentist")
    public ResponseEntity<List<Referral>> getReferralsByDentist(@RequestParam String dentistName) {
        List<Referral> referrals = referralService.findByDentist(dentistName);
        return ResponseEntity.ok(referrals);
    }

    @GetMapping("/status")
    public ResponseEntity<List<Referral>> getReferralsByStatus(@RequestParam String completionStatus) {
        List<Referral> referrals = referralService.findByStatus(completionStatus);
        return ResponseEntity.ok(referrals);
    }
	
    @PostMapping("/create")
    public ResponseEntity<Void> createReferral(@RequestParam Long examId, @RequestParam Long dentistId) {
        referralService.createReferral(examId, dentistId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{referralId}/addExtra")
    public ResponseEntity<Void> addExtra(@PathVariable Long referralId,
                                         @RequestParam("imageLeft") MultipartFile imageLeft,
                                         @RequestParam("imageRight") MultipartFile imageRight) throws IOException {
        Referral referral = referralService.findByReferralId(referralId);
        referralService.addExtra(referral, imageLeft, imageRight, referralId);
        return ResponseEntity.ok().build();
    }


	
}
