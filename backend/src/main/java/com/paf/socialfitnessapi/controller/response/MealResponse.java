package com.paf.socialfitnessapi.controller.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class MealResponse {
    private Long id;
    private String name;
    private String mealType;
    private String recipes;
    private String ingredients;
    private String nutrition;
    private String portion;
    private String instructions;
    private String username;

    private byte[] imageData;
    private MultipartFile image;
}