package com.app.dto;

public class WaterIntakeDTO {
    private Long waterIntakeId;
    private String information;

    // Constructors
    public WaterIntakeDTO() {
    }

    public WaterIntakeDTO(Long waterIntakeId, String information) {
        this.waterIntakeId = waterIntakeId;
        this.information = information;
    }

    // Getters and Setters

    public Long getWaterIntakeId() {
        return waterIntakeId;
    }

    public void setWaterIntakeId(Long waterIntakeId) {
        this.waterIntakeId = waterIntakeId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
