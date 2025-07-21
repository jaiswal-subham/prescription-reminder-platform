package com.example.prescription.reminder.demo.dao;

import com.example.prescription.reminder.demo.utilities.ConstantsValues;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = ConstantsValues.usernameIsRequired)
    String username;

    @NotBlank(message = ConstantsValues.passwordIsRequired)
    String password;

    @Email
    @NotBlank(message = ConstantsValues.emailIdIsRequired)
    String emailId;

    @NotBlank(message = ConstantsValues.firstNameIsRequired)
    String firstName;

    String lastName;

    String roles;
}
