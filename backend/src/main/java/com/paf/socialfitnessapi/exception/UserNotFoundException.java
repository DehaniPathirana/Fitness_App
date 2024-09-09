package com.paf.socialfitnessapi.exception;

public class UserNotFoundException extends NotFoundException{
    public UserNotFoundException(String errorMsg){
        super(errorMsg);
    }
}
