package com.app.controllers;

import com.app.dto.WaterIntakeDTO;
import com.app.entities.WaterIntake;
import com.app.services.IWaterIntakeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class WaterIntakeController {

    @Autowired
    private IWaterIntakeService waterIntakeService;

    // Admin-specific operations

    @PostMapping("/admin/insertwaterintake")
    public ResponseEntity<WaterIntake> createWaterIntake(@RequestBody WaterIntakeDTO waterIntakeDTO) {
        WaterIntake createdWaterIntake = waterIntakeService.createWaterIntake(waterIntakeDTO);
        return ResponseEntity.ok(createdWaterIntake);
    }

    @GetMapping("/admin/getwaterintakes")
    public ResponseEntity<List<WaterIntake>> getAllWaterIntakes() {
        List<WaterIntake> waterIntakes = waterIntakeService.getAllWaterIntakes();
        return ResponseEntity.status(200).body(waterIntakes);
    }

    @GetMapping("/admin/getwaterintakebyid/{id}")
    public ResponseEntity<Optional<WaterIntake>> getWaterIntakeById(@PathVariable Long id) {
        Optional<WaterIntake> waterIntake = waterIntakeService.getWaterIntakeById(id);
        return ResponseEntity.ok(waterIntake);
    }

    @PutMapping("/admin/updatewaterintake")
    public ResponseEntity<WaterIntake> updateWaterIntake(@RequestBody WaterIntakeDTO waterIntakeDTO) {
        WaterIntake waterIntake = waterIntakeService.updateWaterIntake(waterIntakeDTO);
        return ResponseEntity.ok(waterIntake);
    }

    @DeleteMapping("/admin/deletewaterintake")
    public ResponseEntity<?> deleteWaterIntake(@RequestBody Long id) {
        waterIntakeService.deleteWaterIntake(id);
        return ResponseEntity.ok().build();
    }

    // User-specific operations

    @GetMapping("/user/getwaterintakes")
    public ResponseEntity<List<WaterIntake>> getWaterIntakesForUser() {
        List<WaterIntake> waterIntakes = waterIntakeService.getAllWaterIntakes();
        return ResponseEntity.ok(waterIntakes);
    }

    
}
