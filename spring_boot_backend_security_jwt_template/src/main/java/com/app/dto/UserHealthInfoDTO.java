package com.app.dto;

import java.time.LocalDateTime;

public class UserHealthInfoDTO {
    private Long id;
    private String userHealthInformation;
    private Double height;
    private Double weight;
    private Integer age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;  // Updated to use userId instead of Login user

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserHealthInformation() {
        return userHealthInformation;
    }

    public void setUserHealthInformation(String userHealthInformation) {
        this.userHealthInformation = userHealthInformation;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
