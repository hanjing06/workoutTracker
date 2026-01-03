package com.hanjing.workoutTracker.controller;

import com.hanjing.workoutTracker.models.Workout;
import com.hanjing.workoutTracker.services.WorkoutService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WorkoutController {

    private WorkoutService service;

    @PostMapping("/addWorkout")
    public Workout addWorkout(@RequestBody Workout workout){
        return service.createWorkout(workout);
    }

}
