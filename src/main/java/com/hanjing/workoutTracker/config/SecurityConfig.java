package com.hanjing.workoutTracker.config;

import com.hanjing.workoutTracker.filters.JWTAuthenticationFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableWebSecurity
@EnableMethodSecurity

public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService; //authentication

    public SecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter, UserService userService){
        this.jwtAuthenticationFilter=jwtAuthenticationFilter;
        this.userService=userService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ //uses bcrypt hashing (password storage)
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userService.userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception{
                return config.getAuthenticationManager();
    }

    //security filter chain: respoinsible to define available endpoints
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/welcome", "/auth/addNewUser", "/auth/generateToken").permitAll() //public endpoints
                        .requestMatchers("/auth/user/**").hasAuthority("ROLE_USER") //user endpoint
                        .requestMatchers("/auth/admin/**").hasAuthority("ROLE_ADMIN") //admin endpoint
                        .anyRequest().authenticated()) //all other endpoints will require authentication

                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Stateless session

                .authenticationProvider(authenticationProvider()) //set custon authentication provider
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); //defines what its base functionality is

        return http.build();
    }

}
