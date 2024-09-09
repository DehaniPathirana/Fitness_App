package com.paf.socialfitnessapi.service;

import com.paf.socialfitnessapi.controller.request.MealLikeRequest;
import com.paf.socialfitnessapi.controller.request.MealRequest;
import com.paf.socialfitnessapi.controller.response.MealIdResponse;
import com.paf.socialfitnessapi.controller.response.MealResponse;
import com.paf.socialfitnessapi.exception.MealNotFoundException;
import com.paf.socialfitnessapi.exception.UserNotFoundException;
import com.paf.socialfitnessapi.model.Meal;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface MealService {
    MealResponse createmealplan(Long id, MealRequest mealRequest) throws UserNotFoundException, IOException;
    Optional<MealResponse> getmeal(Long id,Long meal_id)throws UserNotFoundException;
    MealIdResponse updatemeal(Long id,Long mId, MealRequest mealRequest) throws UserNotFoundException, MealNotFoundException,IOException;
    String deletemeal(Long id);
    void addlike(Long id, Long id2,MealLikeRequest mealLikeRequest) throws UserNotFoundException,MealNotFoundException;
    List<MealRequest> getAllMeal() throws MealNotFoundException;
}
