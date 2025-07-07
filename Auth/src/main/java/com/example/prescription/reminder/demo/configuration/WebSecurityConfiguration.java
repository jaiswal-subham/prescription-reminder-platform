package com.example.prescription.reminder.demo.configuration;

import com.example.prescription.reminder.demo.filter.JwtFilter;
import com.example.prescription.reminder.demo.repos.UserRepo;
import com.example.prescription.reminder.demo.utilities.ConstantsValues;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfiguration {

    JwtFilter jwtFilter;
    UserRepo userRepo;

    public WebSecurityConfiguration(JwtFilter jwtFilter,UserRepo userRepo) {
        this.jwtFilter = jwtFilter;
        this.userRepo = userRepo;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http.csrf(c -> c.disable())
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers(ConstantsValues.loginRoute).permitAll()
                                .requestMatchers(ConstantsValues.registerRoute).permitAll()
                                .requestMatchers(ConstantsValues.userGenericRoute).authenticated()

                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(new BCryptPasswordEncoder());
        dao.setUserDetailsService(new UserDetail(userRepo));
        return dao;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
