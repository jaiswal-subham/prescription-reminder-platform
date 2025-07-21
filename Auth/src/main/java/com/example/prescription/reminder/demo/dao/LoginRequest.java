package com.example.prescription.reminder.demo.dao;

import com.example.prescription.reminder.demo.utilities.ConstantsValues;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class LoginRequest {

    @NotBlank(message = ConstantsValues.usernameIsRequired)
    private  String username;

    @NotBlank(message = ConstantsValues.passwordIsRequired)
    private  String password;

//    public LoginRequest(String username, String password) {
//        this.username = username;
//        this.password = password;
//    }

}
