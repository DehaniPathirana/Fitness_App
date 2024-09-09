package com.paf.socialfitnessapi.controller;

import com.paf.socialfitnessapi.controller.request.UserLoginRequest;
import com.paf.socialfitnessapi.controller.response.AuthenticationResponse;
import com.paf.socialfitnessapi.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class UserLoginController {

    private LoginService loginService;
    @PostMapping("/user/authenticate")
    public AuthenticationResponse authentication(@RequestBody UserLoginRequest userLoginRequest){
        return loginService.authenticateUser(userLoginRequest);
    }
}
