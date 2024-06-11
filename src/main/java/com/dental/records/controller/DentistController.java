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

import com.dental.records.model.Dentist;
import com.dental.records.model.User;
import com.dental.records.model.UserDentistRequest;
import com.dental.records.service.DentistService;
import com.dental.records.service.UserService;

@RestController
@CrossOrigin(origins = "https://dental-health-record.vercel.app")
public class DentistController {
	private final DentistService dentistService;
	private final UserService userService;

	@Autowired
	public DentistController(DentistService dentistService, UserService userService) {
		this.dentistService = dentistService;
		this.userService = userService;
	}
	
	@GetMapping("alldentists")
	public ResponseEntity<List<Dentist>> viewAllDentists(){
		List<Dentist> allDentists = dentistService.getAllDentists();
		if (allDentists != null && !allDentists.isEmpty()) {
            return ResponseEntity.ok(allDentists);
        } else {
            return ResponseEntity.notFound().build();
        }
	}
	
    @GetMapping("/dentistview/{dentistUserId}")
    public Dentist getDentistProfile(@PathVariable Long dentistUserId) {
    	return dentistService.getDentistProfile(dentistUserId);
    }
    
    @GetMapping("/{dentistId}/fullname")
    public String getFullName(@PathVariable Long dentistId) {
        return dentistService.getFullNameByUserId(dentistId);
    }
	
    @PostMapping("/dentists")
    public ResponseEntity<String> addUser(@RequestBody UserDentistRequest userDentistRequest){
    	User user = userDentistRequest.getUser();
    	Dentist dentist = userDentistRequest.getDentist();
    	return ResponseEntity.ok(dentistService.addUser(user, dentist));
    }


    
    @PutMapping("/editdentist/{username}")
    public ResponseEntity<String> updateDetails(@PathVariable String username, @RequestBody UserDentistRequest userDentistRequest) {
    	User user = userDentistRequest.getUser();
        Dentist dentist = userDentistRequest.getDentist();
        String result = userService.updateDentistDetails(username, user, dentist);
        if ("User and dentist details updated successfully".equals(result)) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

	
}
