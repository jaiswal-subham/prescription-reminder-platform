package com.example.prescription.reminder.demo.configuration;
import com.example.prescription.reminder.demo.entity.UserEntity;
import com.example.prescription.reminder.demo.repos.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.example.prescription.reminder.demo.utilities.ConstantsValues;

@Component
public class UserDetail implements UserDetailsService {

    UserRepo userRepo;

    public UserDetail(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User loadUserByUsername(String username) {
        Optional<UserEntity> userEntityOptional = userRepo.findByUsername(username);
        if(userEntityOptional.isPresent()){
            UserEntity userEntity = userEntityOptional.get();
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            roles.add(new SimpleGrantedAuthority(ConstantsValues.userRole));
            return new User(userEntity.getUsername(), userEntity.getPassword(),roles);
        }else {
            throw new UsernameNotFoundException(ConstantsValues.userNotFoundErrorMessage + username);
        }
    }
}
