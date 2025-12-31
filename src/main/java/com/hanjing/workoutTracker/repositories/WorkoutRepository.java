package com.hanjing.workoutTracker.repositories;

import com.hanjing.workoutTracker.models.User;
import com.hanjing.workoutTracker.models.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findbyUserID(Long userId);
    List<Workout> findByWorkout(String name);
    List<Workout> findByDate(User user, LocalDateTime date);

}
