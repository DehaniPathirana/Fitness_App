package com.paf.socialfitnessapi.exception;

public class MealNotFoundException extends NotFoundException{
    public MealNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
