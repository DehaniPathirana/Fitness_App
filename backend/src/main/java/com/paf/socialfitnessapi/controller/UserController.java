package com.paf.socialfitnessapi.controller;

import com.paf.socialfitnessapi.controller.request.UserRequest;
import com.paf.socialfitnessapi.controller.response.UserIdResponse;
import com.paf.socialfitnessapi.controller.response.UserResponse;
import com.paf.socialfitnessapi.exception.UserNotFoundException;
import com.paf.socialfitnessapi.model.User;
import com.paf.socialfitnessapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class UserController {


    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody UserRequest userRequest)throws IOException {
        return userService.createUser(userRequest);
    }

    @GetMapping("/users/{userId}")
    public UserResponse getUserById(@PathVariable("userId")Long id)throws UserNotFoundException {
        return userService.getUser(id);
    }

    @PutMapping("/users/{user_id}")
    public UserResponse updateUser(@PathVariable("user_id") Long id,@RequestBody UserRequest userRequest)throws UserNotFoundException{
        return userService.updateUser(id,userRequest);
    }

    @DeleteMapping("/users/{user_id}")
    public UserIdResponse deleteuser(@PathVariable("user_id") Long id)throws UserNotFoundException{
        return userService.deleteUser(id);
    }
}
