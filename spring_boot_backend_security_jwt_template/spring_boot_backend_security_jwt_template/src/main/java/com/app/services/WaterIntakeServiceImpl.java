package com.app.services;

import com.app.dto.WaterIntakeDTO;
import com.app.entities.WaterIntake;
import com.app.repositories.WaterIntakeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaterIntakeServiceImpl implements IWaterIntakeService {

    @Autowired
    private WaterIntakeRepository waterIntakeRepository;

    @Override
    public WaterIntake createWaterIntake(WaterIntakeDTO waterIntakeDTO) {
        WaterIntake waterIntake = new WaterIntake();
        waterIntake.setInformation(waterIntakeDTO.getInformation());
        return waterIntakeRepository.save(waterIntake);
    }

    @Override
    public WaterIntake updateWaterIntake(WaterIntakeDTO waterIntakeDTO) {
        Optional<WaterIntake> optionalWaterIntake = waterIntakeRepository.findById(waterIntakeDTO.getWaterIntakeId());
        if (optionalWaterIntake.isPresent()) {
            WaterIntake waterIntake = optionalWaterIntake.get();
            waterIntake.setInformation(waterIntakeDTO.getInformation());
            return waterIntakeRepository.save(waterIntake);
        }
        return null;
    }

    @Override
    public Optional<WaterIntake> getWaterIntakeById(Long id) {
        return waterIntakeRepository.findById(id);
    }

    @Override
    public List<WaterIntake> getAllWaterIntakes() {
        return waterIntakeRepository.findAll();
    }

    @Override
    public void deleteWaterIntake(Long id) {
        waterIntakeRepository.deleteById(id);
    }
}
