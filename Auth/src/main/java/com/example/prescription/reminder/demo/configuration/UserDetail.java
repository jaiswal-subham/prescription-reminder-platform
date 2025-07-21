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
import java.util.Arrays;
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
            String rolesArray[] = userEntity.getRoles().split(",");
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            Arrays.stream(rolesArray).forEach((role) ->roles.add(new SimpleGrantedAuthority(role)));
            return new CustomUser(userEntity.getUsername(), userEntity.getPassword(),roles,userEntity.getId());
        }else {
            throw new UsernameNotFoundException(ConstantsValues.userNotFoundErrorMessage + username);
        }
    }
}
