package com.paf.socialfitnessapi.exception;

import com.paf.socialfitnessapi.controller.response.ErrorMsgResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApControllerAdviser{
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({NotFoundException.class})
    public ErrorMsgResponse handleException(Exception exception){

        ErrorMsgResponse errorMsgResponse = new ErrorMsgResponse();
        errorMsgResponse.setErrorMsg(exception.getMessage());

        return errorMsgResponse;
    }
}
