package com.hanjing.workoutTracker.models;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AuthRequest {

    private String username;
    private String password;

}
