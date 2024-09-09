package com.paf.socialfitnessapi.repository;

import com.paf.socialfitnessapi.model.Meal;
import com.paf.socialfitnessapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal,Long> {
    Optional<Meal> findMealByUserAndId(User user, Long id);
}
