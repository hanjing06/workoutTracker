package com.hanjing.workoutTracker.repositories;

import com.hanjing.workoutTracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //access method that will return if user exists in database
}
