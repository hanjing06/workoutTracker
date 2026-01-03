package com.hanjing.workoutTracker.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AuthRequest {

    private String username;
    private String password;

}
