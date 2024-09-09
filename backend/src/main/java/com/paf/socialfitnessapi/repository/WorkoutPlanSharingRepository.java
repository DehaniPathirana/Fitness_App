package com.paf.socialfitnessapi.repository;

import com.paf.socialfitnessapi.model.User;
import com.paf.socialfitnessapi.model.WorkoutPlanSharing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutPlanSharingRepository extends JpaRepository<WorkoutPlanSharing,Long> {

    List<WorkoutPlanSharing> findWorkoutPlanByUser(User user);
}

