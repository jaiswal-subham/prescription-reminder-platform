package com.example.prescription.reminder.demo.dao;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserResponse {

    String username;

    String emailId;

    String firstName;

    String lastName;

    public UserResponse(String username, String emailId, String firstName, String lastName) {
        this.username = username;
        this.emailId = emailId;
        this.firstName =firstName;
        this.lastName =firstName;
    }
}
