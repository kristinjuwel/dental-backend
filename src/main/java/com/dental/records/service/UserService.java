package com.dental.records.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dental.records.model.Dentist;
import com.dental.records.model.Patient;
import com.dental.records.model.UnverifiedDentist;
import com.dental.records.model.UnverifiedPatient;
import com.dental.records.model.UnverifiedUser;
import com.dental.records.model.User;
import com.dental.records.repository.DentistRepository;
import com.dental.records.repository.PatientRepository;
import com.dental.records.repository.UnverifiedDentistRepository;
import com.dental.records.repository.UnverifiedPatientRepository;
import com.dental.records.repository.UnverifiedUserRepository;
import com.dental.records.repository.UserRepository;

@Service
public class UserService {
	private final UnverifiedUserRepository unverifiedUserRepository;
	private final UnverifiedDentistRepository unverifiedDentistRepository;
	private final UnverifiedPatientRepository unverifiedPatientRepository;
	private final UserRepository userRepository;
	private final DentistRepository dentistRepository;
	private final PatientRepository patientRepository;
	private List<User> loggedInDentists = new ArrayList<>();
	private List<User> loggedInPatients = new ArrayList<>();


	@Autowired
	public UserService(UnverifiedUserRepository unverifiedUserRepository,
			UnverifiedDentistRepository unverifiedDentistRepository,
			UnverifiedPatientRepository unverifiedPatientRepository, UserRepository userRepository,
			DentistRepository dentistRepository, PatientRepository patientRepository,
			List<User> loggedInDentists, List<User> loggedInPatients) {
		super();
		this.unverifiedUserRepository = unverifiedUserRepository;
		this.unverifiedDentistRepository = unverifiedDentistRepository;
		this.unverifiedPatientRepository = unverifiedPatientRepository;
		this.userRepository = userRepository;
		this.dentistRepository = dentistRepository;
		this.patientRepository = patientRepository;
		this.loggedInDentists = loggedInDentists;
		this.loggedInPatients = loggedInPatients;
	}
	

    public boolean verifyUser(String email, Integer otp) {
        UnverifiedUser unvUser = unverifiedUserRepository.findByEmailAndOtp(email, otp);
        if (unvUser != null) {
            Long userId = unvUser.getUserId();
            User user = new User(unvUser);
            userRepository.save(user);

            if ("dentist".equalsIgnoreCase(unvUser.getUserType())) {
                UnverifiedDentist unvDentist = unverifiedDentistRepository.findByDentistId(userId);
                if (unvDentist != null) {
                    Dentist dentist = new Dentist(unvDentist);
                    dentistRepository.save(dentist);
                    unverifiedDentistRepository.deleteById(userId);
                    unverifiedUserRepository.deleteById(userId);
                    return true;
                }
            } else if ("patient".equalsIgnoreCase(unvUser.getUserType())) {
                UnverifiedPatient unvPatient = unverifiedPatientRepository.findByPatientId(userId);
                if (unvPatient != null) {
                    Patient patient = new Patient(unvPatient);
                    patientRepository.save(patient);
                    unverifiedPatientRepository.deleteById(userId);
                    unverifiedUserRepository.deleteById(userId);
                    return true;
                }
                else if ("admin".equalsIgnoreCase(unvUser.getUserType())) {
                    unverifiedUserRepository.deleteById(userId);
                    return true;
               }
            }
        }
        return false;
    }

    public void setLoggedInPatient(User loggedInPatient) {
        // Add the logged-in patient to the list
        loggedInPatients.add(loggedInPatient);
    }

    public List<Long> getLoggedInPatientUserIds() {
        // Return a list of user IDs for all logged-in patients
        return loggedInPatients.stream()
                .map(User::getUserId)
                .collect(Collectors.toList());
    }
    
    public void setLoggedInDentist(User loggedInDentist) {
        // Add the logged-in patient to the list
        loggedInDentists.add(loggedInDentist);
    }

    public List<Long> getLoggedInDentistUserIds() {
        // Return a list of user IDs for all logged-in patients
        return loggedInDentists.stream()
                .map(User::getUserId)
                .collect(Collectors.toList());
    }
    
    public String patientlogin(String identifier, String password) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(identifier);

        // If no user is found with the username, try to find by email
        if (user == null) {
            user = userRepository.findByEmail(identifier);
        }

        if (user != null && bcrypt.matches(password, user.getPassword())) {
            if ("patient".equalsIgnoreCase(user.getUserType())) {
                // Check if the user is marked as deleted
                if ("deleted".equalsIgnoreCase(user.getDeletionStatus())) {
                    return "Login denied: Account has been deleted.";
                }

                // This is a patient account, allow login
                loggedInPatients.add(user);
                return "Login successful";
            } else {
                // This is not a patient account, deny login and print an error message
                return "Login denied: Not a patient account.";
            }
        }

        // If the user is not found or the password doesn't match, deny login
        return "Login denied: Invalid credentials.";
    }
    
    public String dentistlogin(String identifier, String password) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(identifier);

        // If no user is found with the username, try to find by email
        if (user == null) {
            user = userRepository.findByEmail(identifier);
        }

        if (user != null && bcrypt.matches(password, user.getPassword())) {
            if ("dentist".equalsIgnoreCase(user.getUserType())) {
                // Check if the user is marked as deleted
                if ("deleted".equalsIgnoreCase(user.getDeletionStatus())) {
                    return "Login denied: Account has been deleted.";
                }

                // This is a patient account, allow login
                loggedInDentists.add(user);
                return "Login successful";
            } else {
                // This is not a dentist account, deny login and print an error message
                return "Login denied: Not a dentist account.";
            }
        }

        // If the user is not found or the password doesn't match, deny login
        return "Login denied: Invalid credentials.";
    }
	
    public String login(String identifier, String password) {
	    User user = userRepository.findByUsername(identifier);

	    // If no user is found with the username, try to find by email
	    if (user == null) {
	        user = userRepository.findByEmail(identifier);
	    }

	    if (user != null) {
	        if ("patient".equalsIgnoreCase(user.getUserType())) {
	            String result = patientlogin(identifier, password);
	            if (result.equals("Login successful")) {
	                return user.getUserId().toString(); // Return username upon successful login
	            }
	            return result; // Return the result from patientService
	        } else if ("dentist".equalsIgnoreCase(user.getUserType())) {
	            String result = dentistlogin(identifier, password);
	            if (result.equals("Login successful")) {
	                return user.getUserId().toString(); // Return username upon successful login
	            }
	            return result; // Return the result from dentistService
	        } else {
	            return "Invalid user type";
	        }
	    } else {
	        return "User not found";
	    }
	}

	public void logout(String username, String userType) {
	    if ("dentist".equalsIgnoreCase(userType)) {
	        loggedInDentists.removeIf(user -> user.getUsername().equals(username));
	    } else if ("patient".equalsIgnoreCase(userType)) {
	        loggedInPatients.removeIf(user -> user.getUsername().equals(username));
	    }
	}
	
    public String changePassword(String username, String oldPassword, String newPassword) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        User user = userRepository.findByUsername(username);


        if(user != null && bcrypt.matches(oldPassword, user.getPassword())) {
            String encryptedPassword = bcrypt.encode(newPassword);
            user.setPassword(encryptedPassword);
            userRepository.save(user);
            return "Successfully changed password";
        }
        return "Failed to change password";
    }
    
    public String deactivateUser(Long userId) {
        User userToDeactivate = userRepository.findByUserId(userId);
        UnverifiedUser unverifiedUserToDeactivate = unverifiedUserRepository.findByUserId(userId);
        if(userToDeactivate != null && unverifiedUserToDeactivate == null) {
            userToDeactivate.setDeletionStatus("Deleted");
            userRepository.save(userToDeactivate);
            loggedInPatients.removeIf(user -> user.getUserId().equals(userId));
            if ("dentist".equalsIgnoreCase(userToDeactivate.getUserType())) {
    	        loggedInDentists.removeIf(user -> user.getUserId().equals(userId));
    	    } else if ("patient".equalsIgnoreCase(userToDeactivate.getUserType())) {
    	        loggedInPatients.removeIf(user -> user.getUserId().equals(userId));
    	    } 
            return "Patient deletion status set to Deleted";
        } else if(userToDeactivate == null && unverifiedUserToDeactivate != null) {
            unverifiedUserToDeactivate.setDeletionStatus("Deleted");
            unverifiedUserRepository.save(unverifiedUserToDeactivate);
            return "Unverified User deletion status set to Deleted";
        } else {
            return "User not found";
        }
    }
    
    public String updatePatientDetails(String username, User user, Patient patient) {
        // Check if the user is logged in
        if (loggedInPatients == null) {
            return "User not logged in";
        }

        // Find the logged-in user based on the username
        Optional<User> optionalUser = loggedInPatients.stream()
                .filter(loggedInUser -> loggedInUser.getUsername().equals(username))
                .findFirst();

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            Long userId = existingUser.getUserId();

            // Update user details
            existingUser.setFirstName(user.getFirstName());
            existingUser.setMiddleName(user.getMiddleName());
            existingUser.setLastName(user.getLastName());
            existingUser.setAge(user.getAge());
            existingUser.setSex(user.getSex());
            existingUser.setBirthday(user.getBirthday());
            existingUser.setAddress(user.getAddress());
            existingUser.setContactNumber(user.getContactNumber());
            existingUser.setEmail(user.getEmail());
            existingUser.setPicture(user.getPicture());
            existingUser.setSignature(user.getSignature());

            // Find the patient based on the user ID
            Patient existingPatient = patientRepository.findByPatientId(userId);

            if (existingPatient != null) {
                // Update patient details
                existingPatient.setPatientType(patient.getPatientType());
                existingPatient.setRank(patient.getRank());
                existingPatient.setUnitAssign(patient.getUnitAssign());
                
                // Save updated user and patient details
                userRepository.save(existingUser);
                patientRepository.save(existingPatient);

                return "User and patient details updated successfully";
            } else {
                return "Patient not found for the given user ID";
            }
        } else {
            return "User not found for the given username";
        }
    }
    
    public String updateDentistDetails(String username, User user, Dentist dentist) {
        // Check if the user is logged in
        if (loggedInDentists == null) {
            return "User not logged in";
        }

        // Find the logged-in user based on the username
        Optional<User> optionalUser = loggedInDentists.stream()
                .filter(loggedInUser -> loggedInUser.getUsername().equals(username))
                .findFirst();

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            Long userId = existingUser.getUserId();

            // Update user details
            existingUser.setFirstName(user.getFirstName());
            existingUser.setMiddleName(user.getMiddleName());
            existingUser.setLastName(user.getLastName());
            existingUser.setAge(user.getAge());
            existingUser.setSex(user.getSex());
            existingUser.setBirthday(user.getBirthday());
            existingUser.setAddress(user.getAddress());
            existingUser.setContactNumber(user.getContactNumber());
            existingUser.setEmail(user.getEmail());
            existingUser.setPicture(user.getPicture());
            existingUser.setSignature(user.getSignature());

            // Find the patient based on the user ID
            Dentist existingDentist = dentistRepository.findByDentistId(userId);

            if (existingDentist != null) {
                // Update patient details
                existingDentist.setLicenseNo(dentist.getLicenseNo());
                existingDentist.setClinic(dentist.getClinic());
                existingDentist.setClinicLoc(dentist.getClinicLoc());
                // Save updated user and patient details
                userRepository.save(existingUser);
                dentistRepository.save(existingDentist);

                return "User and dentist details updated successfully";
            } else {
                return "Dentist not found for the given user ID";
            }
        } else {
            return "User not found for the given username";
        }
    }
    
    public String updateUser(String username, User user, Object details, String userType) {
        if ("patient".equalsIgnoreCase(userType)) {
            return updatePatientDetails(username, user, (Patient) details);
        } else if ("dentist".equalsIgnoreCase(userType)) {
            return updateDentistDetails(username, user, (Dentist) details);
        } else {
            return "Invalid user type";
        }
    }

    public String uploadProfilePicture(Long userId, MultipartFile file) {
        try {
            User user = userRepository.findByUserId(userId);
            if (user == null) {
                return "User not found";
            }

            // Convert the uploaded file to byte array
            byte[] pictureBytes = file.getBytes();
            // Set the picture in the user entity
            user.setPicture(pictureBytes);
            // Save the updated user entity
            userRepository.save(user);

            return "Profile picture uploaded successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload profile picture";
        }
    }
    
    public User adminAccount(User user) {
    	return userRepository.save(user);
    }

}
