package com.paf.socialfitnessapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WorkoutStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double distanceRun;
    private Integer numberOfPushUpsCompleted;
    private Double weightLifted;
    private Long durationInMillis;
    private Double caloriesBurned;
    private String description;

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @ManyToOne
    private User user;



}
