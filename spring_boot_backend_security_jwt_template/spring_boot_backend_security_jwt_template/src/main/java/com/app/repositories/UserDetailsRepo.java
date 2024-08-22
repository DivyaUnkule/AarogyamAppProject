package com.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.app.entities.Login;

public interface UserDetailsRepo extends JpaRepository<Login, Long> {
	Optional<Login> findByEmail(String email);
	
	
}
