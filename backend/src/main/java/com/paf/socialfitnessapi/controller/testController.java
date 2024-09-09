package com.paf.socialfitnessapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/api/message")
    public String getMessage(){
        return "test API";
    }
}
