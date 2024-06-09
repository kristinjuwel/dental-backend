package com.dental.records.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dental.records.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User findByEmail(String email);
	User findByUserId(Long userId);
}
