package com.paf.socialfitnessapi.repository;

import com.paf.socialfitnessapi.model.User;
import com.paf.socialfitnessapi.model.WorkoutStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutStatusRepository extends JpaRepository<WorkoutStatus,Long> {

    List<WorkoutStatus> findWorkoutStatusByUser(User user);

}
