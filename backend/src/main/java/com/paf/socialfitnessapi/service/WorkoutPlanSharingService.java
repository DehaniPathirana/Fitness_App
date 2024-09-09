package com.paf.socialfitnessapi.service;

import com.paf.socialfitnessapi.controller.request.WorkoutPlanRequest;
import com.paf.socialfitnessapi.controller.response.WorkoutPlanResponse;
import com.paf.socialfitnessapi.exception.NotFoundException;
import com.paf.socialfitnessapi.exception.WorkoutStatusNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface WorkoutPlanSharingService {
    WorkoutPlanResponse createWorkoutPlan(Long userId, WorkoutPlanRequest workoutPlanRequest)throws NotFoundException, IOException;

    List<WorkoutPlanResponse> getWorkoutPlanByUser(Long userId) throws NotFoundException, WorkoutStatusNotFoundException;

    WorkoutPlanResponse updateWorkoutPlan(Long workoutPlanId, WorkoutPlanRequest workoutPlanRequest) throws NotFoundException;
}
