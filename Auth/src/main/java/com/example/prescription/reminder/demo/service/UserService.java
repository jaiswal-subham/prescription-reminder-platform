package com.example.prescription.reminder.demo.service;

import com.example.prescription.reminder.demo.dao.LoginRequest;
import com.example.prescription.reminder.demo.dao.RegisterRequest;
import com.example.prescription.reminder.demo.dao.UserResponse;
import com.example.prescription.reminder.demo.entity.UserEntity;
import com.example.prescription.reminder.demo.repos.UserRepo;
import com.example.prescription.reminder.demo.utilities.ConstantsValues;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    UserRepo userRepo;
    AuthenticationManager authenticationManager;
    JwtService jwtService;

    public UserService( UserRepo userRepo, AuthenticationManager authenticationManager,JwtService jwtService) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public UserResponse findUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepo.findByUsername(username);
        if (userOptional.isPresent()) {
            UserEntity u = userOptional.get();
           return new UserResponse(u.getUsername(),u.getEmailId(),u.getFirstName(),u.getLastName());
        } else {
            throw new UsernameNotFoundException(ConstantsValues.userNotFoundErrorMessage);
        }

    }


    public String login(LoginRequest loginRequest){


        Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        if(authenticate.isAuthenticated()){

            return jwtService.generateToken(loginRequest.getUsername());
        }   return  ConstantsValues.loginFailed;

    }

    public String register(RegisterRequest registerRequest){

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.getPassword());
       UserEntity userEntity = new UserEntity(registerRequest.getUsername(),encryptedPassword,registerRequest.getEmailId(),registerRequest.getFirstName(),registerRequest.getLastName());

       try{
           this.userRepo.save(userEntity);
           return ConstantsValues.success;
       }
       catch (Exception e){
           throw  e;
       }

    }

}

