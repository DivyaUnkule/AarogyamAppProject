package com.app.dto;

public class ProgressDTO {
    private Long progressId;
    private double bmi;
    private double newWeight;
    private Long userId;

    // Constructors
    public ProgressDTO() {}

    public ProgressDTO(Long progressId, double bmi, double newWeight, Long userId) {
        this.progressId = progressId;
        this.bmi = bmi;
        this.newWeight = newWeight;
        this.userId = userId;
    }

    // Getters and Setters
    public Long getProgressId() {
        return progressId;
    }

    public void setProgressId(Long progressId) {
        this.progressId = progressId;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public double getNewWeight() {
        return newWeight;
    }

    public void setNewWeight(double newWeight) {
        this.newWeight = newWeight;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
