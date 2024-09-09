package com.paf.socialfitnessapi.repository;

import com.paf.socialfitnessapi.model.LikeCount;
import com.paf.socialfitnessapi.model.MealLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MealLikeRepository extends JpaRepository<MealLike,Long> {
    @Query("SELECT m.meal.id AS mealId, COUNT(m) AS likeCount FROM MealLike m GROUP BY m.meal")
    List<LikeCount> findLikeCountsByMeal();
}
