package com.example.prescription.reminder.demo.dao;

import com.example.prescription.reminder.demo.utilities.ConstantsValues;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = ConstantsValues.usernameIsRequired)
    private  String username;

    @NotBlank(message = ConstantsValues.passwordIsRequired)
    private  String password;

}
