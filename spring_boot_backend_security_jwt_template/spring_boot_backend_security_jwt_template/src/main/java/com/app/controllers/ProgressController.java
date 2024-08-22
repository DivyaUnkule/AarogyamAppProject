package com.app.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.dto.ProgressDTO;
import com.app.entities.Progress;
import com.app.repositories.ProgressRepository;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class ProgressController {

	@Autowired
	private ProgressRepository progressRepository;

	@GetMapping("/getprogressbyid{userId}")
	public ResponseEntity<ProgressDTO> getProgressByUserId(@PathVariable Long userId) {
		Progress progress = progressRepository.findByIdUserId(userId);
		ProgressDTO progressDTO = new ProgressDTO(progress.getProgressId(), progress.getBmi(), progress.getNewWeight(),
				progress.getUser().getId());

		return ResponseEntity.ok(progressDTO);
	}
	
	
	@GetMapping("/admin/getalluserprogress")
    public ResponseEntity<List<ProgressDTO>> getAllUserProgress() {
        List<ProgressDTO> progressList = progressRepository.findAll().stream()
                .map(progress -> new ProgressDTO(
                        progress.getProgressId(),
                        progress.getBmi(),
                        progress.getNewWeight(),
                        progress.getUser().getId()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(progressList);
    }
	
	
	
}