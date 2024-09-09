package com.paf.socialfitnessapi.controller;


import com.paf.socialfitnessapi.controller.request.WorkoutStatusRequest;
import com.paf.socialfitnessapi.controller.response.WorkoutStatusResponse;
import com.paf.socialfitnessapi.exception.NotFoundException;
import com.paf.socialfitnessapi.exception.WorkoutStatusNotFoundException;
import com.paf.socialfitnessapi.service.serviceImpl.WorkoutStatusServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class WorkoutStatusController {

    private WorkoutStatusServiceImpl workoutStatusService;

    @PostMapping("/users/{userId}/workoutstatus")
    public ResponseEntity<WorkoutStatusResponse> createWorkoutStatus(
            @PathVariable("userId") Long userId,
            @RequestParam("distanceRun") Double distanceRun,
            @RequestParam("numberOfPushUpsCompleted") Integer numberOfPushUpsCompleted,
            @RequestParam("weightLifted") Double weightLifted,
            @RequestParam("durationInMillis") Long durationInMillis,
            @RequestParam("caloriesBurned") Double caloriesBurned,
            @RequestParam("description") String description,
            @RequestParam(value = "imageData", required = false) MultipartFile imageData
    ) throws NotFoundException, IOException {
        WorkoutStatusRequest workoutStatusRequest = WorkoutStatusRequest.builder()
                .distanceRun(distanceRun)
                .numberOfPushUpsCompleted(numberOfPushUpsCompleted)
                .weightLifted(weightLifted)
                .durationInMillis(durationInMillis)
                .caloriesBurned(caloriesBurned)
                .description(description)
                .imageData(imageData)
                .build();

        WorkoutStatusResponse workoutStatusResponse = workoutStatusService.createWorkoutStatus(userId, workoutStatusRequest);
        return ResponseEntity.ok(workoutStatusResponse);
    }



    @GetMapping("/users/{userId}/workoutstatus")
    public List<WorkoutStatusResponse> getAllWorkoutStatus(@PathVariable("userId") Long userId) throws NotFoundException, WorkoutStatusNotFoundException {
        return workoutStatusService.getWorkoutStatusByUser(userId);
    }


    @PutMapping("/workoutstatus/{statusId}")
    public WorkoutStatusResponse updateWorkoutStatus(@PathVariable("statusId") Long statusId, @RequestBody WorkoutStatusRequest workoutStatusRequest) throws NotFoundException{
        return  workoutStatusService.updateWorkoutStatus(statusId,workoutStatusRequest);
    }
}

