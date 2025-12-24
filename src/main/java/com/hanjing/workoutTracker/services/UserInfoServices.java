package com.hanjing.workoutTracker.services;

import com.hanjing.workoutTracker.models.User;
import com.hanjing.workoutTracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoServices {
    private final UserRepository repository;
    private final PasswordEncoder encoder;

    @Autowired
    public UserInfoServices(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch user from the database by email (username)
        Optional<User> userInfo = repository.findByEmail(username);

        if (userInfo.isEmpty()) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        // Convert UserInfo to UserDetails (UserInfoDetails)
        User user = userInfo.get();
        return new User(user.getEmail(), user.getPassword(), user.getRoles());
    }

    public String addUser(User userInfo) {
        // Encrypt password before saving
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User added succesfully";
    }
}