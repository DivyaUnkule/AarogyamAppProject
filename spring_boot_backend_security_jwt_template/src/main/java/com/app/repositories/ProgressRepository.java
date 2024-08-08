package com.app.repositories;

import com.app.entities.Progress;
import com.app.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByUser(Login user);
}
