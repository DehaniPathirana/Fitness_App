package com.paf.socialfitnessapi.service.serviceImpl;

import com.paf.socialfitnessapi.controller.request.WorkoutStatusRequest;
import com.paf.socialfitnessapi.controller.response.WorkoutStatusResponse;
import com.paf.socialfitnessapi.exception.NotFoundException;
import com.paf.socialfitnessapi.exception.WorkoutStatusNotFoundException;
import com.paf.socialfitnessapi.model.User;
import com.paf.socialfitnessapi.model.WorkoutStatus;
import com.paf.socialfitnessapi.repository.UserRepository;
import com.paf.socialfitnessapi.repository.WorkoutStatusRepository;
import com.paf.socialfitnessapi.service.WorkoutStatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WorkoutStatusServiceImpl implements WorkoutStatusService {

    private WorkoutStatusRepository workoutStatusRepository;
    private UserRepository userRepository;

    @Override
    public WorkoutStatusResponse createWorkoutStatus(Long userId, WorkoutStatusRequest workoutStatusRequest)  throws NotFoundException, IOException {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User Not Found With Id : " + userId)
        );

        WorkoutStatus workoutStatus = new WorkoutStatus();
        workoutStatus.setUser(user);

        byte[] imageData = null;
        if (workoutStatusRequest.getImageData() != null) {
            imageData = workoutStatusRequest.getImageData().getBytes();
        }
        workoutStatus.setImageData(imageData);
        workoutStatus.setDistanceRun(workoutStatusRequest.getDistanceRun());
        workoutStatus.setNumberOfPushUpsCompleted(workoutStatusRequest.getNumberOfPushUpsCompleted());
        workoutStatus.setWeightLifted(workoutStatusRequest.getWeightLifted());
        workoutStatus.setDurationInMillis(workoutStatusRequest.getDurationInMillis());
        workoutStatus.setCaloriesBurned(workoutStatusRequest.getCaloriesBurned());
        workoutStatus.setDescription(workoutStatusRequest.getDescription());

        workoutStatusRepository.save(workoutStatus);

        WorkoutStatusResponse workoutStatusResponse = WorkoutStatusResponse.builder()
                .id(workoutStatus.getId())
                .caloriesBurned(workoutStatus.getCaloriesBurned())
                .distanceRun(workoutStatus.getDistanceRun())
                .weightLifted(workoutStatus.getWeightLifted())
                .durationInMillis(workoutStatus.getDurationInMillis())
                .caloriesBurned(workoutStatus.getCaloriesBurned())
                .imageData(workoutStatus.getImageData())
                .description(workoutStatus.getDescription()).build();

        return workoutStatusResponse;
    }


    @Override
    public List<WorkoutStatusResponse> getWorkoutStatusByUser(Long userId) throws NotFoundException, WorkoutStatusNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(
                ()->new NotFoundException("user Not found with id "+userId)
        );

        List<WorkoutStatus> workoutStatusList= workoutStatusRepository.findWorkoutStatusByUser(user);

        if(workoutStatusList.isEmpty())
            throw new WorkoutStatusNotFoundException("Workout Status Not Found  with Id");


        List<WorkoutStatusResponse> responseList = new ArrayList<>();
        for (WorkoutStatus workoutStatus : workoutStatusList) {
            WorkoutStatusResponse response = WorkoutStatusResponse.builder()
                    .id(workoutStatus.getId())
                    .distanceRun(workoutStatus.getDistanceRun())
                    .numberOfPushUpsCompleted(workoutStatus.getNumberOfPushUpsCompleted())
                    .weightLifted(workoutStatus.getWeightLifted())
                    .durationInMillis(workoutStatus.getDurationInMillis())
                    .caloriesBurned(workoutStatus.getCaloriesBurned())
                    .description(workoutStatus.getDescription())
                    .imageData(workoutStatus.getImageData())
                    .build();
            responseList.add(response);
        }
        return responseList;


    }


    @Override
    public WorkoutStatusResponse updateWorkoutStatus(Long statusId, WorkoutStatusRequest workoutStatusRequest) throws NotFoundException {

        WorkoutStatus workoutStatus= workoutStatusRepository.findById(statusId).orElseThrow(
                () -> new NotFoundException("Workout Status Not Found  with Id "+ statusId)
        );
        workoutStatus.setDistanceRun(workoutStatusRequest.getDistanceRun());
        workoutStatus.setNumberOfPushUpsCompleted(workoutStatusRequest.getNumberOfPushUpsCompleted());
        workoutStatus.setWeightLifted(workoutStatusRequest.getWeightLifted());
        workoutStatus.setDurationInMillis(workoutStatusRequest.getDurationInMillis());
        workoutStatus.setCaloriesBurned(workoutStatusRequest.getCaloriesBurned());
        workoutStatus.setDescription(workoutStatusRequest.getDescription());

        workoutStatusRepository.save(workoutStatus);


        WorkoutStatusResponse workoutStatusResponse = WorkoutStatusResponse.builder()
                .id(workoutStatus.getId())
                .caloriesBurned(workoutStatus.getCaloriesBurned())
                .distanceRun(workoutStatus.getDistanceRun())
                .weightLifted(workoutStatus.getWeightLifted())
                .durationInMillis(workoutStatus.getDurationInMillis())
                .caloriesBurned(workoutStatus.getCaloriesBurned())
                .description(workoutStatus.getDescription()).build();

        return workoutStatusResponse;
    }


}
