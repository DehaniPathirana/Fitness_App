package com.paf.socialfitnessapi.controller.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class WorkoutStatusRequest {
    private Double distanceRun;
    private Integer numberOfPushUpsCompleted;
    private Double weightLifted;
    private Long durationInMillis;
    private Double caloriesBurned;
    private String description;

    private MultipartFile imageData;
}