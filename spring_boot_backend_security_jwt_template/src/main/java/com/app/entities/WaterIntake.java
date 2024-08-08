package com.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "WaterIntake")
public class WaterIntake extends BaseEntity {

    

    @Column(columnDefinition = "TEXT")
    private String information;

    // Constructors
    public WaterIntake() {
    }

    public WaterIntake(String information) {
        this.information = information;
    }

    // Getters and Setters

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
