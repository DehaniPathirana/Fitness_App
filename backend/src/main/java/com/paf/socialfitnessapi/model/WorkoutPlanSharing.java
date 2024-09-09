package com.paf.socialfitnessapi.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class WorkoutPlanSharing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String planName;
//    private String description;
//    private String routine;

//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "workout_plan_id") // Assuming Exercise has a reference to WorkoutPlanSharing
//    private List<Exercise> exerciseList;

    private String workoutPlanDescription;
    private String routineType;
    private String exerciseType;
    private String exerciseDescription;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @ManyToOne
    private User user;
}