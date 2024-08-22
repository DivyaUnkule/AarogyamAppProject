package com.app.services;

import com.app.dto.WaterIntakeDTO;
import com.app.entities.WaterIntake;

import java.util.List;
import java.util.Optional;

public interface IWaterIntakeService {

    WaterIntake createWaterIntake(WaterIntakeDTO waterIntakeDTO);

    WaterIntake updateWaterIntake(WaterIntakeDTO waterIntakeDTO);

    Optional<WaterIntake> getWaterIntakeById(Long id);

    List<WaterIntake> getAllWaterIntakes();

    void deleteWaterIntake(Long id);
}
