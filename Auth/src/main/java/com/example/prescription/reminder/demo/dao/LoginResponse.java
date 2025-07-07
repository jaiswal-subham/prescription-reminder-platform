package com.example.prescription.reminder.demo.dao;

import lombok.Data;

@Data
public class LoginResponse {

    String authenticationToken;

    public LoginResponse(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
}
