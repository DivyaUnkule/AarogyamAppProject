package com.app.repositories;

import com.app.entities.UserHealthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHealthInfoRepository extends JpaRepository<UserHealthInfo, Long> {

    // Custom query method to find all UserHealthInfo records by user ID
    UserHealthInfo findByUserId(Long userId);
}
