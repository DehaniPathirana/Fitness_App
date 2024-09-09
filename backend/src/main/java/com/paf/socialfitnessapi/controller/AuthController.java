package com.paf.socialfitnessapi.controller;

import com.paf.socialfitnessapi.controller.request.UserRequest;
import com.paf.socialfitnessapi.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class AuthController {

    private UserService userService;

    @GetMapping("/auth")
    public String home(@AuthenticationPrincipal OAuth2User principal) {
        System.out.println("/sample"+principal);
        return "home";
    }

    @GetMapping("/oauth2/authorization/google")
    @ResponseBody
    public ResponseEntity<Object> getUsername(@AuthenticationPrincipal OAuth2User principal) throws IOException {
        if (principal == null) {
            System.out.println("TEst");
            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");
            UserRequest userRequest = new UserRequest();
            userRequest.setEmail(email);
            userRequest.setFirstName(name);
            userRequest.setSource("google");

            return userService.createUser(userRequest);
        } else {
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
        }
    }
}

