package com.hanjing.workoutTracker.services;

import com.hanjing.workoutTracker.models.User;
import com.hanjing.workoutTracker.models.Workout;
import com.hanjing.workoutTracker.repositories.WorkoutRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @RolesAllowed("User")
    public Workout createWorkout(Workout workout){
        workout.setTitle(workout.getTitle());
        return workoutRepository.save(workout);
    }

    public Workout updateWorkout(){
        User user;
        LocalDateTime update;

    }

    public Workout deleteWorkout(){

        deleteWorkout();
    }


    /*public void weeklyReport(){
        idk maybe make an ai model to summarize everything
    }*/

}
