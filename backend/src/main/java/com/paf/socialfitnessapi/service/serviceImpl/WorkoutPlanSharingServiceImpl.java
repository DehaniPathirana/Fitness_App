package com.paf.socialfitnessapi.service.serviceImpl;

import com.paf.socialfitnessapi.controller.request.WorkoutPlanRequest;
import com.paf.socialfitnessapi.controller.request.WorkoutStatusRequest;
import com.paf.socialfitnessapi.controller.response.WorkoutPlanResponse;
import com.paf.socialfitnessapi.exception.NotFoundException;
import com.paf.socialfitnessapi.exception.WorkoutStatusNotFoundException;
import com.paf.socialfitnessapi.model.User;
import com.paf.socialfitnessapi.model.WorkoutPlanSharing;
import com.paf.socialfitnessapi.repository.UserRepository;
import com.paf.socialfitnessapi.repository.WorkoutPlanSharingRepository;
import com.paf.socialfitnessapi.service.WorkoutPlanSharingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutPlanSharingServiceImpl implements WorkoutPlanSharingService {

    private UserRepository userRepository;
    private WorkoutPlanSharingRepository workoutPlanSharingRepository;
    @Override
    public WorkoutPlanResponse createWorkoutPlan(Long userId, WorkoutPlanRequest workoutPlanRequest) throws NotFoundException, IOException {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User Not Found With Id : " + userId)
        );

        WorkoutPlanSharing workoutPlanSharing = new WorkoutPlanSharing();

        workoutPlanSharing.setPlanName(workoutPlanRequest.getPlanName());
        workoutPlanSharing.setUser(user);
        byte[] imageData = null;
        if (workoutPlanRequest.getImageData() != null) {
            imageData = workoutPlanRequest.getImageData().getBytes();
        }
        workoutPlanSharing.setImageData(imageData);
        workoutPlanSharing.setWorkoutPlanDescription(workoutPlanRequest.getWorkoutPlanDescription());
        workoutPlanSharing.setRoutineType(workoutPlanRequest.getRoutineType());
        workoutPlanSharing.setExerciseType(workoutPlanRequest.getExerciseType());
        workoutPlanSharing.setExerciseDescription(workoutPlanRequest.getExerciseDescription());


        workoutPlanSharingRepository.save(workoutPlanSharing);

        WorkoutPlanResponse workoutPlanResponse = WorkoutPlanResponse.builder()
                .id(workoutPlanSharing.getId())
                .planName(workoutPlanSharing.getPlanName())
                .workoutPlanDescription(workoutPlanSharing.getWorkoutPlanDescription())
                .routineType(workoutPlanSharing.getRoutineType())
                .exerciseType(workoutPlanSharing.getExerciseType())
                .exerciseDescription(workoutPlanSharing.getExerciseDescription())
                .imageData(workoutPlanSharing.getImageData())
                .build();

        return workoutPlanResponse;
    }

    @Override
    public List<WorkoutPlanResponse> getWorkoutPlanByUser(Long userId) throws NotFoundException, WorkoutStatusNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()->new NotFoundException("user Not found with id "+userId)
        );

        List<WorkoutPlanSharing> workoutPlanSharingList = workoutPlanSharingRepository.findWorkoutPlanByUser(user);

        if(workoutPlanSharingList.isEmpty())
            throw new WorkoutStatusNotFoundException("Workout PLan Not Found  with Id");


        List<WorkoutPlanResponse> responseList = new ArrayList<>();

        for (WorkoutPlanSharing workoutPlanSharing : workoutPlanSharingList) {
            WorkoutPlanResponse response = WorkoutPlanResponse.builder()
                    .id(workoutPlanSharing.getId())
                    .planName(workoutPlanSharing.getPlanName())
                    .workoutPlanDescription(workoutPlanSharing.getWorkoutPlanDescription())
                    .routineType(workoutPlanSharing.getRoutineType())
                    .exerciseType(workoutPlanSharing.getExerciseType())
                    .exerciseDescription(workoutPlanSharing.getExerciseDescription())
                    .imageData(workoutPlanSharing.getImageData())
                    .build();
            responseList.add(response);
        }

        return responseList;

    }

    @Override
    public WorkoutPlanResponse updateWorkoutPlan(Long workoutPlanId, WorkoutPlanRequest workoutPlanRequest) throws NotFoundException {
        return null;
    }
}

