package com.paf.socialfitnessapi.controller.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class WorkoutPlanRequest {
    private String planName;
//    private String description;
//    private String routine;
    private String workoutPlanDescription;
    private String routineType;
    private String exerciseType;
    private String exerciseDescription;
    private MultipartFile imageData;
}


