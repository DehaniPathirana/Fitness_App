package com.paf.socialfitnessapi.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WorkoutPlanResponse {
    private Long id;
    private String planName;
//    private String description;
//    private String routine;
    private String workoutPlanDescription;
    private String routineType;
    private String exerciseType;
    private String exerciseDescription;

    private byte[] imageData;
}
