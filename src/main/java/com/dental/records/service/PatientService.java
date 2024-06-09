package com.dental.records.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dental.records.model.Patient;
import com.dental.records.model.UnverifiedPatient;
import com.dental.records.model.UnverifiedUser;
import com.dental.records.model.User;
import com.dental.records.repository.PatientRepository;
import com.dental.records.repository.UnverifiedPatientRepository;
import com.dental.records.repository.UnverifiedUserRepository;
import com.dental.records.repository.UserRepository;


@Service
public class PatientService {
	private final UnverifiedUserRepository unverifiedUserRepository;
	private final UnverifiedPatientRepository unverifiedPatientRepository;
	private final UserRepository userRepository;
	private final PatientRepository patientRepository;
	private final JavaMailSender javaMailSender;
	
	@Autowired
	public PatientService(UnverifiedUserRepository unverifiedUserRepository,
			UnverifiedPatientRepository unverifiedPatientRepository, UserRepository userRepository,
			PatientRepository patientRepository, JavaMailSender javaMailSender) {
		this.unverifiedUserRepository = unverifiedUserRepository;
		this.unverifiedPatientRepository = unverifiedPatientRepository;
		this.userRepository = userRepository;
		this.patientRepository = patientRepository;
		this.javaMailSender = javaMailSender;
	}
	
    public void sendVerification(String email, Integer code) {
        String subject = "Account Verification";
        sendVerificationEmail(email, subject, code);
	}
    
	private void sendVerificationEmail(String to, String subject, Integer code) {
        SimpleMailMessage message = new SimpleMailMessage();
        String link = "http://localhost:8080/verify?email=" + to + "&otp=" + code;
        message.setTo(to);
        message.setSubject(subject);
        message.setText("To verify your account, please enter this code in the verification page: " + String.valueOf(code) + "\n\nOr follow this link in your browser: " + link);
        javaMailSender.send(message);
    }
    
    private Integer generateVerificationCode() {
    	Random random = new Random();
        int min = 100000;
        int max = 999999;
        int randomNumber = random.nextInt(max - min + 1) + min;
        return randomNumber;
    }
    
    public String addUser(User user, Patient patient) {
        User u = userRepository.findByUsername(user.getUsername());
        User uemail = userRepository.findByEmail(user.getEmail());
        UnverifiedUser unv = unverifiedUserRepository.findByUsername(user.getUsername());
        UnverifiedUser unvemail = unverifiedUserRepository.findByEmail(user.getEmail());
        
        if (u != null || unv != null) {
            return "Username is already in use.";
        } else {
            if (uemail != null) {
                if (uemail.getDeletionStatus() != "Deleted") return "Email is already in use by a non-Deleted Account.";
            }
            if (unvemail != null) {
                if (unvemail.getDeletionStatus() != "Deleted") return "Email is already in use by a non-Deleted Account.";
            }
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String encryptedPassword = bcrypt.encode(user.getPassword());
            user.setPassword(encryptedPassword);
            int otp = generateVerificationCode();

            sendVerification(user.getEmail(), otp);

            UnverifiedUser newUser = new UnverifiedUser(user, otp);
            UnverifiedPatient newPatient = new UnverifiedPatient(patient);

            // Save the unverified user and patient to their respective repositories
            unverifiedUserRepository.save(newUser);
            newPatient.setPatientId(newUser.getUserId());
            unverifiedPatientRepository.save(newPatient);

            return "User registered successfully";
        }
    }


 
    public List<Patient> getAllUsers() {
        // Retrieve all registered users from the UserRepository
        return patientRepository.findAll();
    }


    public Patient getPatientProfile(Long patientUserId) {
    	return patientRepository.findByPatientId(patientUserId);
    }
    
    
    

}
