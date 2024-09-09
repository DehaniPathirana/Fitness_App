package com.paf.socialfitnessapi.service.serviceImpl;

import com.paf.socialfitnessapi.controller.request.MealLikeRequest;
import com.paf.socialfitnessapi.controller.request.MealRequest;
import com.paf.socialfitnessapi.controller.response.MealIdResponse;
import com.paf.socialfitnessapi.controller.response.MealResponse;
import com.paf.socialfitnessapi.exception.MealNotFoundException;
import com.paf.socialfitnessapi.exception.UserNotFoundException;
import com.paf.socialfitnessapi.model.Meal;
import com.paf.socialfitnessapi.model.MealLike;
import com.paf.socialfitnessapi.model.User;
import com.paf.socialfitnessapi.repository.MealLikeRepository;
import com.paf.socialfitnessapi.repository.MealRepository;
import com.paf.socialfitnessapi.repository.UserRepository;
import com.paf.socialfitnessapi.service.MealService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class MealServiceImpl implements MealService {
    MealRepository mealRepository;
    UserRepository userRepository;
    MealLikeRepository mealLikeRepository;

    @Override
    public MealResponse createmealplan(Long id, MealRequest mealRequest) throws UserNotFoundException, IOException {

        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found ");
        }
        User user=userOptional.get();

        byte[] imageData = null;
        if (mealRequest.getImageData() != null) {
//            imageData = mealRequest.getImageData().getBytes();
            imageData = mealRequest.getImageData();
        }

        Meal meal = new Meal();
        meal.setName(mealRequest.getName());
        meal.setMealType(mealRequest.getMealType());
        meal.setIngredients(mealRequest.getIngredients());
        meal.setInstructions(mealRequest.getInstructions());
        meal.setNutrition(mealRequest.getNutrition());
        meal.setPortion(mealRequest.getPortion());
        meal.setRecipes(mealRequest.getRecipes());
        meal.setImageData(imageData);
        meal.setUser(user);
        mealRepository.save(meal);

        MealResponse mealResponse = MealResponse.builder()
//                .id(meal.getId())
                .mealType(meal.getMealType())
                .ingredients(meal.getInstructions())
                .instructions(meal.getInstructions())
                .nutrition(meal.getNutrition())
                .portion(meal.getPortion())
                .recipes(meal.getRecipes())
                .imageData(meal.getImageData()).build();

        return mealResponse;
    }

        @Override
        public Optional<MealResponse> getmeal (Long id,Long mId)throws UserNotFoundException{

            User user = userRepository.findById(id).orElseThrow(
                    ()->new UserNotFoundException("User nOt found")
            );

            Optional<Meal> mealOptional = mealRepository.findById(mId);
            Meal meal = mealOptional.get();

            if(mealOptional.isPresent()) {
                return Optional.of(MealResponse.builder()
                        .id(meal.getId())
                        .name(meal.getName())
                        .mealType(meal.getMealType())
                        .ingredients(meal.getIngredients())
                        .recipes(meal.getRecipes())
                        .nutrition(meal.getNutrition())
                        .portion(meal.getPortion())
                        .instructions(meal.getInstructions())
                        .imageData(meal.getImageData())
                        .username(user.getFirstName())
                        .build());
            }
            return null;
        }

        @Override
        public MealIdResponse updatemeal (Long id, Long mId, MealRequest mealRequest)throws MealNotFoundException,UserNotFoundException {

            User user = userRepository.findById(id).orElseThrow(
                    ()->new UserNotFoundException("User Not Found "+id)
            );

            Meal meal = mealRepository.findMealByUserAndId(user,mId).orElseThrow(
                    ()->new MealNotFoundException("No meal")
            );

            meal.setName(mealRequest.getName());
            meal.setMealType(mealRequest.getMealType());
            meal.setIngredients(mealRequest.getIngredients());
            meal.setInstructions(mealRequest.getInstructions());
            meal.setNutrition(mealRequest.getNutrition());
            meal.setPortion(mealRequest.getPortion());
            meal.setRecipes(mealRequest.getRecipes());
            meal.setImageData(mealRequest.getImageData());
            mealRepository.save(meal);

            MealIdResponse mealIdResponse = new MealIdResponse();
            mealIdResponse.setMsg("updated");
            return mealIdResponse;
        }

        @Override
        public String deletemeal (Long id){
            if (!mealRepository.existsById(id)) {
                return ("no id found");
            }
            mealRepository.deleteById(id);
            return ("delete id " + id);
        }

    @Override
    public void addlike(Long userid, Long mealid,MealLikeRequest mealLikeRequest) throws UserNotFoundException,MealNotFoundException{

        Optional<User> userOptional = userRepository.findById(userid);
        Optional<Meal> mealOptional=mealRepository.findById(mealid);

        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("User not found ");
        }
        if (!mealOptional.isPresent()) {
            throw new MealNotFoundException("Meal not found ");
        }
        User user=userOptional.get();
        Meal meal = mealOptional.get();

        MealLike mealLike=new MealLike();

        mealLike.setMeal(meal);
        mealLike.setLikedUser(user);
        mealLike.setPostOwner(meal.getUser());
        mealLike.setStatus(mealLikeRequest.getStatus());

        mealLikeRepository.save(mealLike);
    }

    @Override
    public List<MealRequest> getAllMeal() throws MealNotFoundException {
        List<Meal> mealList = mealRepository.findAll();
        if (mealList.isEmpty())
            throw new MealNotFoundException("Meals Not Found");

        List<MealRequest> mealRequestList = new ArrayList<>();
        for (Meal meal : mealList) {
            mealRequestList.add(MealRequest.builder()
                    .name(meal.getName())
                    .mealType(meal.getMealType())
                    .ingredients(meal.getIngredients())
                    .recipes(meal.getRecipes())
                    .nutrition(meal.getNutrition())
                    .portion(meal.getPortion())
                    .instructions(meal.getInstructions())
                    .imageData(meal.getImageData())
                    .build());
        }
        return mealRequestList;
    }


}

