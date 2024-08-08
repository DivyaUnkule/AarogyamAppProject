package com.app.controllers;

import com.app.dto.UserHealthInfoDTO;
import com.app.entities.UserHealthInfo;
import com.app.services.IUserHealthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserHealthInfoController {

    @Autowired
    private IUserHealthInfoService userHealthInfoService;

    // Admin-specific operations

    @PostMapping("/admin/insertuserhealthinfo")
    public ResponseEntity<UserHealthInfo> createUserHealthInfo(@RequestBody UserHealthInfoDTO userHealthInfoDTO) {
        UserHealthInfo createdUserHealthInfo = userHealthInfoService.createUserHealthInfo(userHealthInfoDTO);
        return ResponseEntity.ok(createdUserHealthInfo);
    }

    @GetMapping("/admin/getuserhealthinfos")
    public ResponseEntity<List<UserHealthInfo>> getAllUserHealthInfos() {
        List<UserHealthInfo> userHealthInfos = userHealthInfoService.getAllUserHealthInfos();
        return ResponseEntity.status(200).body(userHealthInfos);
    }

    @GetMapping("/admin/getuserhealthinfobyId/{id}")
    public ResponseEntity<Optional<UserHealthInfo>> getUserHealthInfoById(@PathVariable Long id) {
        Optional<UserHealthInfo> userHealthInfo = userHealthInfoService.getUserHealthInfoById(id);
        return ResponseEntity.ok(userHealthInfo);
    }

    @PutMapping("/admin/updateuserhealthinfo")
    public ResponseEntity<UserHealthInfo> updateUserHealthInfo(@RequestBody UserHealthInfoDTO userHealthInfoDTO) {
        UserHealthInfo updatedUserHealthInfo = userHealthInfoService.updateUserHealthInfo(userHealthInfoDTO);
        return ResponseEntity.ok(updatedUserHealthInfo);
    }

    @DeleteMapping("/admin/deleteuserhealthinfo")
    public ResponseEntity<?> deleteUserHealthInfo(@RequestBody Long id) {
        userHealthInfoService.deleteUserHealthInfo(id);
        return ResponseEntity.ok().build();
    }

    // User-specific operations

    @PostMapping("/user/insertuserhealthinfo")
    public ResponseEntity<UserHealthInfo> createUserHealthInfoForUser(@RequestBody UserHealthInfoDTO userHealthInfoDTO) {
        UserHealthInfo createdUserHealthInfo = userHealthInfoService.createUserHealthInfo(userHealthInfoDTO);
        return ResponseEntity.ok(createdUserHealthInfo);
    }

    @PutMapping("/user/updateuserhealthinfo")
    public ResponseEntity<UserHealthInfo> updateUserHealthInfoForUser(@RequestBody UserHealthInfoDTO userHealthInfoDTO) {
        UserHealthInfo updatedUserHealthInfo = userHealthInfoService.updateUserHealthInfo(userHealthInfoDTO);
        return ResponseEntity.ok(updatedUserHealthInfo);
    }

    @GetMapping("/user/getuserhealthinfobyId/{id}")
    public ResponseEntity<Optional<UserHealthInfo>> getUserHealthInfoByIdForUser(@PathVariable Long id) {
        Optional<UserHealthInfo> userHealthInfo = userHealthInfoService.getUserHealthInfoById(id);
        return ResponseEntity.ok(userHealthInfo);
    }

    @GetMapping("/user/getuserhealthinfosbyuserid/{userId}")
    public ResponseEntity<UserHealthInfo> getUserHealthInfosByUserId(@PathVariable Long userId) {
        UserHealthInfo userHealthInfo = userHealthInfoService.getUserHealthInfosByUserId(userId);
        return ResponseEntity.status(200).body(userHealthInfo);
    }
}
