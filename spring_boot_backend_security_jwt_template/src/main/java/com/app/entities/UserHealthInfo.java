package com.app.entities;

import javax.persistence.*;



import java.time.LocalDateTime;

@Entity
@Table(name = "UserHealthInfo")
public class UserHealthInfo extends BaseEntity {

    @Column(name = "UserHealthInformation", columnDefinition = "TEXT")
    private String userHealthInformation;

    @Column(name = "height", precision = 5, scale = 2)
    private Double height;

    @Column(name = "Weight", precision = 5, scale = 2)
    private Double weight;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "ID", nullable = true)
    private Login user;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters

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

    public Login getUser() {
        return user;
    }

    public void setUser(Login user) {
        this.user = user;
    }
}

