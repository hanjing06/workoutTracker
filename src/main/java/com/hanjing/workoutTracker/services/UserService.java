package com.hanjing.workoutTracker.services;

import com.hanjing.workoutTracker.models.User;
import com.hanjing.workoutTracker.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor

public class UserService { //userdetails
    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            // TODO: lookup user from DB
            if (!username.equals("test")) {
                throw new UsernameNotFoundException("User not found: " + username);
            }

            return User.getUsername("test")
                    .password(passwordEncoder().encode("123"))
                    .roles("USER")
                    .build();
        };
    }

    public User save(User newUser){
        if(newUser.getId()==null){
            newUser.setCreatedAt(LocalDateTime.now());
        }
        newUser.setUpdatedAt(LocalDateTime.now());
    }
}
