package com.app.services;

import com.app.dto.UserHealthInfoDTO;
import com.app.entities.UserHealthInfo;

import java.util.List;
import java.util.Optional;

public interface IUserHealthInfoService {

    UserHealthInfo createUserHealthInfo(UserHealthInfoDTO userHealthInfoDTO);

    UserHealthInfo updateUserHealthInfo(UserHealthInfoDTO userHealthInfoDTO);

    Optional<UserHealthInfo> getUserHealthInfoById(Long id);

    List<UserHealthInfo> getAllUserHealthInfos();

    UserHealthInfo getUserHealthInfosByUserId(Long userId);

    void deleteUserHealthInfo(Long id);
}
