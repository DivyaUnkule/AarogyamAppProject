package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Login;

public interface UserRepo extends JpaRepository<Login, Long> {
	
}
