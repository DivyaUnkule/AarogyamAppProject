package com.app.controllers;


import com.app.dto.ExerciseYogaDTO;
import com.app.entities.ExerciseYoga;
import com.app.repositories.UserRepo;
import com.app.services.IExerciseYogaService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/users")
public class ExerciseYogaController {

    @Autowired
    private IExerciseYogaService exerciseYogaService;
    
    @Autowired
    UserRepo userrepo;

 // Create a new ExerciseYoga (Admin only)
    @PostMapping("/admin/insertexercise")
    public ResponseEntity<ExerciseYoga> createExerciseYoga( @RequestBody ExerciseYogaDTO exerciseYogaDTO) {
        ExerciseYoga createdExerciseYoga = exerciseYogaService.createExerciseYoga(exerciseYogaDTO);
        return ResponseEntity.ok(createdExerciseYoga);
    }
    
    @GetMapping("/admin/getexercises")
    public ResponseEntity<List<ExerciseYoga>> getAllExerciseYoga() {
    	List<ExerciseYoga> exerciseyoga = exerciseYogaService.getAllExerciseYoga();
        return ResponseEntity.status(200).body(exerciseyoga);
    }
    
    @GetMapping("/admin/getexercisebyId")
    public ResponseEntity<Optional<ExerciseYoga>> getExerciseYogaById(@RequestBody Long id) {
    	Optional<ExerciseYoga> exerciseyoga = exerciseYogaService.getExerciseYogaById(id);
        return ResponseEntity.ok(exerciseyoga);
    }

    @PutMapping("/admin/updateexercise")
    public ResponseEntity<ExerciseYoga> updateExerciseYoga(@RequestBody ExerciseYogaDTO exerciseyogadto) {
        ExerciseYoga exerciseyoga = exerciseYogaService.updateExerciseYoga(exerciseyogadto);
        return ResponseEntity.ok(exerciseyoga);
    }

    @DeleteMapping("/admin/deleteexercise")
    public ResponseEntity<?> deleteExerciseYoga(@RequestBody Long id) {
        exerciseYogaService.deleteExerciseYoga(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/weightlossuser/exercise")
    public List<ExerciseYoga> getAllWeightLossUserExercises() {
        return exerciseYogaService.getAllWeightLossUserExercises();
    }

    @GetMapping("/weightgainuser/exercise")
    public List<ExerciseYoga> getAllWeightGainUserExercises() {
        return exerciseYogaService.getAllWeightGainUserExercises();
    }

    @GetMapping("/regularuser/exercise")
    public List<ExerciseYoga> getAllRegularUserExercises() {
        return exerciseYogaService.getAllRegularUserExercises();
    }
}
