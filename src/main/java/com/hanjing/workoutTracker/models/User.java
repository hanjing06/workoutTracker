package com.hanjing.workoutTracker.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String roles;

    @Column(unique = true)
    private String email;

}
