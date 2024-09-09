package com.paf.socialfitnessapi.exception;

public class WorkoutStatusNotFoundException extends NotFoundException {
    public WorkoutStatusNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
