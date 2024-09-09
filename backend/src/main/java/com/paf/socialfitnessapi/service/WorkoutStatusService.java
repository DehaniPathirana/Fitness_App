package com.paf.socialfitnessapi.service;

import com.paf.socialfitnessapi.controller.request.WorkoutStatusRequest;
import com.paf.socialfitnessapi.controller.response.WorkoutStatusResponse;
import com.paf.socialfitnessapi.exception.NotFoundException;
import com.paf.socialfitnessapi.exception.WorkoutStatusNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface WorkoutStatusService {
    WorkoutStatusResponse createWorkoutStatus(Long userId, WorkoutStatusRequest workoutStatusRequest)throws NotFoundException, IOException;

    List<WorkoutStatusResponse> getWorkoutStatusByUser(Long userTd) throws NotFoundException, WorkoutStatusNotFoundException;

    WorkoutStatusResponse updateWorkoutStatus(Long statusId, WorkoutStatusRequest workoutStatusRequest) throws NotFoundException;
}
