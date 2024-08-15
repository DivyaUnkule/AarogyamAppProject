package com.app.services;

import com.app.dto.UserHealthInfoDTO;
import com.app.entities.Progress;
import com.app.entities.UserHealthInfo;
import com.app.repositories.ProgressRepository;
import com.app.repositories.UserHealthInfoRepository;
import com.app.repositories.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserHealthInfoServiceImpl implements IUserHealthInfoService {

    @Autowired
    private UserHealthInfoRepository userHealthInfoRepository;

    @Autowired
    private UserRepo userRepository;
    
    @Autowired
    private ProgressRepository progressrepository;

    @Override
    public UserHealthInfo createUserHealthInfo(UserHealthInfoDTO userHealthInfoDTO) {
        UserHealthInfo userHealthInfo = new UserHealthInfo();
        userHealthInfo.setUserHealthInformation(userHealthInfoDTO.getUserHealthInformation());
        userHealthInfo.setHeight(userHealthInfoDTO.getHeight());
        userHealthInfo.setWeight(userHealthInfoDTO.getWeight());
        userHealthInfo.setAge(userHealthInfoDTO.getAge());
        userHealthInfo.setCreatedAt(LocalDateTime.now());
        userHealthInfo.setUpdatedAt(LocalDateTime.now());

        // Fetch user using userId from DTO and set it in userHealthInfo
        userRepository.findById(userHealthInfoDTO.getUserId())
                      .ifPresent(userHealthInfo::setUser);

        return userHealthInfoRepository.save(userHealthInfo);
    }

    @Override
    public UserHealthInfo updateUserHealthInfo(UserHealthInfoDTO userHealthInfoDTO) {
        Optional<UserHealthInfo> existingUserHealthInfoOpt = userHealthInfoRepository.findById(userHealthInfoDTO.getId());

        if (existingUserHealthInfoOpt.isPresent()) {
            UserHealthInfo existingUserHealthInfo = existingUserHealthInfoOpt.get();
            
            // Update UserHealthInfo
            existingUserHealthInfo.setUserHealthInformation(userHealthInfoDTO.getUserHealthInformation());
            existingUserHealthInfo.setHeight(userHealthInfoDTO.getHeight());
            existingUserHealthInfo.setWeight(userHealthInfoDTO.getWeight());
            existingUserHealthInfo.setAge(userHealthInfoDTO.getAge());
            existingUserHealthInfo.setUpdatedAt(LocalDateTime.now());

            // Save UserHealthInfo
            UserHealthInfo updatedUserHealthInfo = userHealthInfoRepository.save(existingUserHealthInfo);

           // Update Progress
            Progress progress = progressrepository.findByUser(existingUserHealthInfo.getUser())
                    .orElse(new Progress(existingUserHealthInfo.getUser(), existingUserHealthInfo.getWeight(), (userHealthInfoDTO.getWeight() / (userHealthInfoDTO.getHeight() * userHealthInfoDTO.getHeight()))));

            progress.setNewWeight(userHealthInfoDTO.getWeight());
            progress.setBmi(Math.round((userHealthInfoDTO.getWeight() / (userHealthInfoDTO.getHeight() * userHealthInfoDTO.getHeight())) * 100.0) / 100.0);
            
            progressrepository.save(progress);

            return updatedUserHealthInfo;
        } else {
            throw new RuntimeException("UserHealthInfo with id " + userHealthInfoDTO.getId() + " not found");
        }
    }


    @Override
    public Optional<UserHealthInfo> getUserHealthInfoById(Long id) {
        return userHealthInfoRepository.findById(id);
    }

    @Override
    public List<UserHealthInfo> getAllUserHealthInfos() {
        return userHealthInfoRepository.findAll();
    }

    @Override
    public UserHealthInfo getUserHealthInfosByUserId(Long userId) {
        return userHealthInfoRepository.findByUserId(userId);
                
    }

    @Override
    public void deleteUserHealthInfo(Long id) {
        userHealthInfoRepository.deleteById(id);
    }
}
