package com.app.dto;

import com.app.enums.Role;

public class SigninResponse {
    private String jwt;
    private String msg;
    private Role role;
    private Long userId;  // Added userId field

    // Getters and Setters
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public Long getUserId() {  // Getter for userId
        return userId;
    }

    public void setUserId(Long userId) {  // Setter for userId
        this.userId = userId;
    }

    // Constructor
    public SigninResponse(String jwt, Role role, Long userId, String msg) {
        super();
        this.jwt = jwt;
        this.role = role;
        this.userId = userId;
        this.msg = msg;
    }

    public SigninResponse() {
        super();
    }

    @Override
    public String toString() {
        return "SigninResponse [jwt=" + jwt + ", msg=" + msg + ", role=" + role + ", userId=" + userId + "]";
    }
}
