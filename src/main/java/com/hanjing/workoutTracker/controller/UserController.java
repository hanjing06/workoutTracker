package com.hanjing.workoutTracker.controller; //controllers handle signup/login requests and authorized requests

import com.hanjing.workoutTracker.services.JWTService;
import com.hanjing.workoutTracker.models.AuthRequest;
import com.hanjing.workoutTracker.models.User;

import com.hanjing.workoutTracker.services.UserInfoServices;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class UserController {

    private UserInfoServices service;

    private JWTService jwtService;

    private AuthenticationManager authenticationManager;

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody User userInfo){
        return service.addUser(userInfo);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
