package com.app.repositories;

import com.app.entities.Progress;
import com.app.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
	
    Optional<Progress> findByUser(Login user);

    @Query("SELECT p FROM Progress p WHERE p.user.id = :userId")
	Progress findByIdUserId(@Param("userId") Long userId);
}
