package com.paf.socialfitnessapi.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String errorMsg){
        super(errorMsg);
    }
}
