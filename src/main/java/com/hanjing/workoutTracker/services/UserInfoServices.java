package com.hanjing.workoutTracker.services;

import com.hanjing.workoutTracker.models.User;
import com.hanjing.workoutTracker.repositories.UserRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServices implements UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserInfoServices(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database by email (username)
        Optional<User> userInfo = repository.findByEmail(username);

        // Convert UserInfo to UserDetails (UserInfoDetails)
        User user = repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new UserInfoDetails(user);
    }

    public String addUser(User userInfo) {
        // Encrypt password before saving
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User added succesfully";
    }
}