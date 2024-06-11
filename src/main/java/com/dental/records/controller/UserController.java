package com.dental.records.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dental.records.model.User;
import com.dental.records.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/adminCreate")
    public User makeAdmin(@RequestBody User user) {
    	return userService.adminAccount(user);
    }
    
    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam("email") String email,
            								@RequestParam("otp") Integer otp) {
    	if (userService.verifyUser(email, otp)) {
    		return ResponseEntity.ok("Successful verification.");
    	} else {
    		return ResponseEntity.ok("Unsuccessful verification.");
    	}
    }
	
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam("identifier") String identifier,
                                        @RequestParam("password") String password) {
        String username = userService.login(identifier, password);

        if (!username.startsWith("Login denied")) {
            return ResponseEntity.ok(username);
        } else {
            // If the login failed or if the user is deleted, return UNAUTHORIZED status
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(username);
        }
    }
    
    @PostMapping("/logout/{username}/{userType}")
    public ResponseEntity<String> logout(@PathVariable String username, @PathVariable String userType) {
    	userService.logout(username, userType);
        return ResponseEntity.ok("Logged out successfully");
    }
    
    @PostMapping("/changePass")
    public ResponseEntity<String> changePassword(
            @RequestParam ("username") String username,
            @RequestParam ("oldPassword") String oldPassword,
            @RequestParam ("newPassword") String newPassword) {
        return ResponseEntity.ok(userService.changePassword(username, oldPassword, newPassword));
    }
    
    @PutMapping("/upload/{userId}")
    public ResponseEntity<String> uploadProfilePicture(@PathVariable Long userId, @RequestBody MultipartFile file) {
        try {
            String result = userService.uploadProfilePicture(userId, file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload profile picture: " + e.getMessage());
        }
    }
    
    @DeleteMapping("/users")
    public ResponseEntity<String> deleteUser(@RequestParam("userId") Long userId) {
        return ResponseEntity.ok(userService.deactivateUser(userId));
    }
    
	
}
