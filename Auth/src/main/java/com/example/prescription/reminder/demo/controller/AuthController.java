package com.example.prescription.reminder.demo.controller;

import com.example.prescription.reminder.demo.dao.LoginRequest;
import com.example.prescription.reminder.demo.dao.LoginResponse;
import com.example.prescription.reminder.demo.dao.RegisterRequest;
import com.example.prescription.reminder.demo.dao.RegisterResponse;

import com.example.prescription.reminder.demo.service.UserService;
import com.example.prescription.reminder.demo.utilities.ConstantsValues;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class AuthController {

    UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(ConstantsValues.loginRoute)
    ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        System.out.println("HI");
        String login = this.userService.login(loginRequest);
        if(login.equals(ConstantsValues.loginFailed)) return new ResponseEntity<LoginResponse>(new LoginResponse(login), HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<LoginResponse>(new LoginResponse(login), HttpStatus.OK);
    }

    @PostMapping(ConstantsValues.registerRoute)
    ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest){
        String registration  =  this.userService.register(registerRequest);

        if(registration.equals(ConstantsValues.registrationFailed)) return new ResponseEntity<RegisterResponse>(new RegisterResponse(registration), HttpStatus.EXPECTATION_FAILED);
        return new ResponseEntity<RegisterResponse>(new RegisterResponse(registration), HttpStatus.CREATED);
    }




}
