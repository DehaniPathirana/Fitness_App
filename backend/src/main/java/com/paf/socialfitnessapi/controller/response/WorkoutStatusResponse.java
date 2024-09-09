package com.paf.socialfitnessapi.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkoutStatusResponse {
    private Long id;
    private Double distanceRun;
    private Integer numberOfPushUpsCompleted;
    private Double weightLifted;
    private Long durationInMillis;
    private Double caloriesBurned;
    private String description;

    private byte[] imageData;
}