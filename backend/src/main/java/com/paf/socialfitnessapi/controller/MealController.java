package com.paf.socialfitnessapi.controller;

import com.paf.socialfitnessapi.controller.request.MealLikeRequest;
import com.paf.socialfitnessapi.controller.request.MealRequest;
import com.paf.socialfitnessapi.controller.response.MealIdResponse;
import com.paf.socialfitnessapi.controller.response.MealResponse;
import com.paf.socialfitnessapi.exception.MealNotFoundException;
import com.paf.socialfitnessapi.exception.UserNotFoundException;
import com.paf.socialfitnessapi.model.LikeCount;
import com.paf.socialfitnessapi.model.Meal;
import com.paf.socialfitnessapi.repository.MealLikeRepository;
import com.paf.socialfitnessapi.service.MealService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@AllArgsConstructor
@RestController
@CrossOrigin("*")
public class MealController {

    MealService mealService;
    MealLikeRepository mealLikeRepository;

    @PostMapping("/user/{user-id}/meals")
    public ResponseEntity<MealResponse> createmeal(
            @PathVariable("user-id") Long id,
            @RequestParam("name") String name,
            @RequestParam("mealType") String mealType,
            @RequestParam("ingredients") String ingredients,
            @RequestParam("instructions") String instructions,
            @RequestParam("nutrition") String nutrition,
            @RequestParam("portion") String portion,
            @RequestParam("recipes") String recipes,
            @RequestParam(value = "imageData", required = false) MultipartFile imageData

    ) throws UserNotFoundException, IOException {
        MealRequest mealRequest = MealRequest.builder()
                .name(name)
                .mealType(mealType)
                .ingredients(ingredients)
                .instructions(instructions)
                .nutrition(nutrition)
                .portion(portion)
                .recipes(recipes)
                .imageData(imageData.getBytes())
                .build();

        MealResponse mealResponse = mealService.createmealplan(id,mealRequest);
        return ResponseEntity.ok(mealResponse);
    }

    @GetMapping("users/{user-id}/meals/{meal-id}")
    public Optional<MealResponse> getmeal(@PathVariable("user-id") Long id,@PathVariable("meal-id")Long mealId)throws UserNotFoundException{
        return mealService.getmeal(id,mealId);
    }

    @GetMapping("meals")
    public List<MealRequest> getAllMeal() throws MealNotFoundException{
        return mealService.getAllMeal();
    }

    @PutMapping("users/{user-id}/meals/{meal-id}")
    public ResponseEntity<MealIdResponse> updatemeal(
        @PathVariable("user-id") Long id, @PathVariable("meal-id") Long mealId,
        @RequestParam("name") String name,
        @RequestParam("mealType") String mealType,
        @RequestParam("ingredients") String ingredients,
        @RequestParam("instructions") String instructions,
        @RequestParam("nutrition") String nutrition,
        @RequestParam("portion") String portion,
        @RequestParam("recipes") String recipes,
        @RequestParam(value = "imageData", required = false) MultipartFile imageData

    ) throws UserNotFoundException,MealNotFoundException ,IOException {
            MealRequest mealRequest = MealRequest.builder()
                    .name(name)
                    .mealType(mealType)
                    .ingredients(ingredients)
                    .instructions(instructions)
                    .nutrition(nutrition)
                    .portion(portion)
                    .recipes(recipes)
                    .imageData(imageData.getBytes())
                    .build();

            MealIdResponse mealIdResponse = mealService.updatemeal(id,mealId,mealRequest);
            return ResponseEntity.ok(mealIdResponse);
    }






    @DeleteMapping("user/{user-id}/meals/{meal-id}")
    public String deletemeal(@PathVariable("user-id") Long id){
        return mealService.deletemeal(id);
    }

    @PostMapping("users/{user-id}/meals/{meal-id}")
    public void addlike(@PathVariable("user-id")Long userid,@PathVariable("meal-id")Long mealid,@RequestBody MealLikeRequest mealLikeRequest) throws UserNotFoundException,MealNotFoundException{
        mealService.addlike(userid,mealid,mealLikeRequest);
    }

    @GetMapping("/counts")
    public ResponseEntity<List<Map<String, Long>>> getLikeCounts() {
        List<LikeCount> likeCounts = mealLikeRepository.findLikeCountsByMeal();

        List<Map<String, Long>> likeCountList = new ArrayList<>();
        Map<String, Long> likeCountMap = null;
        for (LikeCount projection : likeCounts) {
            likeCountMap = new HashMap<>();
            likeCountMap.put("mealId", projection.getMealId());
            likeCountMap.put("likeCount", projection.getLikeCount());
            likeCountList.add(likeCountMap);
        }

        return ResponseEntity.ok(likeCountList);

    }

}
