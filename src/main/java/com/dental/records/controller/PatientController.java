package com.dental.records.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dental.records.model.Patient;
import com.dental.records.model.User;
import com.dental.records.model.UserPatientRequest;
import com.dental.records.service.PatientService;
import com.dental.records.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {
	private final PatientService patientService;
	private final UserService userService;

	
	@Autowired
	public PatientController(PatientService patientService, UserService userService) {
		this.patientService = patientService;
		this.userService = userService;
	}
	
    @GetMapping("/allpatients")
    public ResponseEntity<List<Patient>> viewAllUsers() {
        List<Patient> allUsers = patientService.getAllUsers();
        if (allUsers != null && !allUsers.isEmpty()) {
            return ResponseEntity.ok(allUsers);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/patientview/{patientUserId}")
    public Patient getPatientProfile(@PathVariable Long patientUserId) {
        return patientService.getPatientProfile(patientUserId);
    }
    
    @PostMapping("/patients")
    public ResponseEntity<String> addUser(@RequestBody UserPatientRequest userPatientRequest) {
        User user = userPatientRequest.getUser();
        Patient patient = userPatientRequest.getPatient();
        return ResponseEntity.ok(patientService.addUser(user, patient));
    }
    
    
    @PutMapping("/editpatient/{username}")
    public ResponseEntity<String> updateDetails(@PathVariable String username, @RequestBody UserPatientRequest userPatientRequest) {
    	User user = userPatientRequest.getUser();
    	Patient patient = userPatientRequest.getPatient();
        String result = userService.updatePatientDetails(username, user, patient);
        if ("User and dentist details updated successfully".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }



}
