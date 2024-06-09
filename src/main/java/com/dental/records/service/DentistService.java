package com.dental.records.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dental.records.model.Dentist;
import com.dental.records.model.UnverifiedDentist;
import com.dental.records.model.UnverifiedUser;
import com.dental.records.model.User;
import com.dental.records.repository.DentistRepository;
import com.dental.records.repository.UnverifiedDentistRepository;
import com.dental.records.repository.UnverifiedUserRepository;
import com.dental.records.repository.UserRepository;

@Service
public class DentistService {
	private final UnverifiedUserRepository unverifiedUserRepository;
	private final UnverifiedDentistRepository unverifiedDentistRepository;
	private final UserRepository userRepository;
	private final DentistRepository dentistRepository;
	private final JavaMailSender javaMailSender;
	
	@Autowired
	public DentistService(UnverifiedUserRepository unverifiedUserRepository,
			UnverifiedDentistRepository unverifiedDentistRepository, UserRepository userRepository,
			DentistRepository dentistRepository, JavaMailSender javaMailSender) {
		this.unverifiedUserRepository = unverifiedUserRepository;
		this.unverifiedDentistRepository = unverifiedDentistRepository;
		this.userRepository = userRepository;
		this.dentistRepository = dentistRepository;
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
    
    public String addUser(User user, Dentist dentist) {
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
            UnverifiedDentist newDentist = new UnverifiedDentist(dentist);

            // Save the unverified user and patient to their respective repositories
            unverifiedUserRepository.save(newUser);
            newDentist.setDentistId(newUser.getUserId());
            unverifiedDentistRepository.save(newDentist);

            return "User registered successfully";
        }
    }
	
    public List<Dentist> getAllDentists() {
        // Retrieve all registered users from the UserRepository
        return dentistRepository.findAll();
    }


    public Dentist getDentistProfile(Long dentistUserId) {
    	return dentistRepository.findByDentistId(dentistUserId);
    }
    
    public String getFullNameByUserId(Long dentistUserId) {
        Optional<User> userOptional = userRepository.findById(dentistUserId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String middleInitial = user.getMiddleName() != null && !user.getMiddleName().isEmpty() ? user.getMiddleName().substring(0, 1) + ". " : "";
            return user.getFirstName() + " " + middleInitial + user.getLastName();
        } else {
            return ("Dentist not found with id: " + dentistUserId);
        }
    }
    
    
}
