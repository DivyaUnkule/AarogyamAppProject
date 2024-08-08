package com.app.services;

import com.app.custom_exception_handler.ResourceNotFoundException;
import com.app.dto.ExerciseYogaDTO;
import com.app.entities.ExerciseYoga;
import com.app.entities.RoleEntity;
import com.app.enums.Role;
import com.app.repositories.ExerciseYogaRepository;
import com.app.repositories.RoleEntityRepo;
import com.app.services.ExerciseYogaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

@Service
 @Transactional
 public class ExerciseYogaServiceImpl implements IExerciseYogaService {

        @Autowired
        private ExerciseYogaRepository exerciseYogaRepository;

        @Autowired
        private RoleEntityRepo roleRepository;

        @Override
        public ExerciseYoga createExerciseYoga(ExerciseYogaDTO exerciseYogaDTO) {
        	ExerciseYoga exerciseyoga = new ExerciseYoga();
        	exerciseyoga.setName(exerciseYogaDTO.getName());
        	exerciseyoga.setDescription(exerciseYogaDTO.getDescription());
            RoleEntity role = roleRepository.findById(exerciseYogaDTO.getRoleid())
                    .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + exerciseYogaDTO.getRoleid()));
            exerciseyoga.setRole(role);
            return exerciseYogaRepository.save(exerciseyoga);
        }
        
        @Override
        public List<ExerciseYoga> getAllExerciseYoga() {
            return exerciseYogaRepository.findAll();
        }
        
       @Override
    public Optional<ExerciseYoga> getExerciseYogaById(Long id) {
        return exerciseYogaRepository.findById(id);
    }
    
       @Override
       public ExerciseYoga updateExerciseYoga(ExerciseYogaDTO exerciseyogadto) {
           
           ExerciseYoga existingExerciseYoga = exerciseYogaRepository.findById(exerciseyogadto.getId())
                   .orElseThrow(() -> new ResourceNotFoundException("ExerciseYoga not found with id: " + exerciseyogadto.getId()));

           
           existingExerciseYoga.setName(exerciseyogadto.getName());
           existingExerciseYoga.setDescription(exerciseyogadto.getDescription());

           
           RoleEntity role = roleRepository.findById(exerciseyogadto.getRoleid())
                   .orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + exerciseyogadto.getRoleid()));
           existingExerciseYoga.setRole(role);

           
           return exerciseYogaRepository.save(existingExerciseYoga);
       }
       
       @Override
       public void deleteExerciseYoga(Long id) {
           if (!exerciseYogaRepository.existsById(id)) {
               throw new ResourceNotFoundException("ExerciseYoga not found with id: " + id);
           }
           exerciseYogaRepository.deleteById(id);
       }
       
       @Override
       public List<ExerciseYoga> getAllWeightLossUserExercises() {
           return exerciseYogaRepository.findByRoleRoleName(Role.ROLE_WEIGHTLOSSUSER);
       }
       
       @Override
       public List<ExerciseYoga> getAllWeightGainUserExercises() {
           return exerciseYogaRepository.findByRoleRoleName(Role.ROLE_WEIGHTGAINUSER);
       }
       
       @Override
       public List<ExerciseYoga> getAllRegularUserExercises() {
           return exerciseYogaRepository.findByRoleRoleName(Role.ROLE_REGULARUSER);
       }

	

	

	
}
