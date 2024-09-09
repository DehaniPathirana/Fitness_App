package com.paf.socialfitnessapi.controller;

import com.paf.socialfitnessapi.controller.request.WorkoutPlanRequest;
import com.paf.socialfitnessapi.controller.response.WorkoutPlanResponse;
import com.paf.socialfitnessapi.exception.NotFoundException;
import com.paf.socialfitnessapi.exception.WorkoutStatusNotFoundException;
import com.paf.socialfitnessapi.service.WorkoutPlanSharingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class WorkoutPlanController {
    private WorkoutPlanSharingService workoutPlanSharingService;

//    @PostMapping("/users/{userId}/workoutplans")
//    public WorkoutPlanResponse createWorkoutPlan(@PathVariable Long userId, @RequestBody WorkoutPlanRequest workoutPlanRequest)throws NotFoundException, IOException {
//        return workoutPlanSharingService.createWorkoutPlan(userId,workoutPlanRequest);
//    }


    @PostMapping("/users/{userId}/workoutplans")
    public ResponseEntity<WorkoutPlanResponse> createWorkoutPlan(
            @PathVariable("userId") Long userId,
            @RequestParam("planName") String planName,
            @RequestParam("workoutPlanDescription") String workoutPlanDescription,
            @RequestParam("routineType") String routineType,
            @RequestParam("exerciseType") String exerciseType,
            @RequestParam("exerciseDescription") String exerciseDescription,
            @RequestParam(value = "imageData", required = false) MultipartFile imageData
    ) throws NotFoundException, IOException {
        WorkoutPlanRequest workoutPlanRequest = WorkoutPlanRequest.builder()
                .planName(planName)
                .workoutPlanDescription(workoutPlanDescription)
                .routineType(routineType)
                .exerciseType(exerciseType)
                .exerciseDescription(exerciseDescription)
                .imageData(imageData)
                .build();

        WorkoutPlanResponse workoutPlanResponse = workoutPlanSharingService.createWorkoutPlan(userId, workoutPlanRequest);
        return ResponseEntity.ok(workoutPlanResponse);
    }

    @GetMapping("/users/{userId}/workoutplans")
    public List<WorkoutPlanResponse> getAllWorkoutPlans(@PathVariable("userId") Long userId) throws NotFoundException, WorkoutStatusNotFoundException {
        return workoutPlanSharingService.getWorkoutPlanByUser(userId);
    }

}
