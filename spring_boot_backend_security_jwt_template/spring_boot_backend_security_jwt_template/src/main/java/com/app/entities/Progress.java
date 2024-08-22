package com.app.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Progress")
public class Progress implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long progressId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", nullable = false)
    private Login user;

    @Column(name = "newWeight", precision = 4, scale = 2)
    private Double newWeight;

    @Column(name = "bmi", precision = 4, scale = 2)
    private Double bmi;

    // Constructors
    public Progress() {
    }

    public Progress(Login user, Double newWeight, Double bmi) {
        this.user = user;
        this.newWeight = newWeight;
        this.bmi = bmi;
    }

    // Getters and Setters
    public Long getProgressId() {
        return progressId;
    }

    public Login getUser() {
        return user;
    }

    public void setUser(Login user) {
        this.user = user;
    }

    public Double getNewWeight() {
        return newWeight;
    }

    public void setNewWeight(Double newWeight) {
        this.newWeight = newWeight;
        
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(double updatedbmi) {
       
            this.bmi = updatedbmi;
        }
    }

