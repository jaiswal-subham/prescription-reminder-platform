package com.example.prescription.reminder.demo.dao;

import lombok.Data;

@Data
public class RegisterResponse {

    String status;

    public RegisterResponse(String success) {
        this.status = success;
    }
}

