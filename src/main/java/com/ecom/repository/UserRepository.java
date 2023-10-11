package com.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	
	Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

}
