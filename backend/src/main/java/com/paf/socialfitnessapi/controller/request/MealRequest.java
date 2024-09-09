package com.paf.socialfitnessapi.controller.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class MealRequest {

    private Long id;
    private String name;
    private String mealType;
    private String recipes;
    private String ingredients;
    private String nutrition;
    private String portion;
    private String instructions;

//    private MultipartFile imageData;
    private byte[] imageData;

}
