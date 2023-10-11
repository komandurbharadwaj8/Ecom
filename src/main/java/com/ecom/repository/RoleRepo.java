package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{
	
	
	Role findByName(String name);


}
