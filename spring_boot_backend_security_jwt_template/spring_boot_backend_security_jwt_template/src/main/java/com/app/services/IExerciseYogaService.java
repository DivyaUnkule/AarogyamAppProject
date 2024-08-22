package com.app.services;

import com.app.dto.ExerciseYogaDTO;
import com.app.entities.ExerciseYoga;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;



public interface IExerciseYogaService {
	ExerciseYoga createExerciseYoga(ExerciseYogaDTO exerciseYogaDTO);
	List<ExerciseYoga> getAllExerciseYoga();
	Optional<ExerciseYoga> getExerciseYogaById(Long id);
	void deleteExerciseYoga(Long id);
	ExerciseYoga updateExerciseYoga(ExerciseYogaDTO exerciseyogadto);
	List<ExerciseYoga> getAllWeightLossUserExercises();
	List<ExerciseYoga> getAllWeightGainUserExercises();
	List<ExerciseYoga> getAllRegularUserExercises();
	
	
}
