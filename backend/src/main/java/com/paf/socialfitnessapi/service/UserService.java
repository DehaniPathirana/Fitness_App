package com.paf.socialfitnessapi.service;

import com.paf.socialfitnessapi.controller.request.UserRequest;
import com.paf.socialfitnessapi.controller.response.UserIdResponse;
import com.paf.socialfitnessapi.controller.response.UserResponse;
import com.paf.socialfitnessapi.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface UserService {
    ResponseEntity<Object>  createUser(UserRequest userRequest)throws IOException;
//    ResponseEntity<Object> createUser(UserRequest userRequest) throws IOException;
    UserResponse getUser(Long id)throws UserNotFoundException;
    UserResponse updateUser(Long id,UserRequest userRequest)throws UserNotFoundException;
    UserIdResponse deleteUser(Long id)throws UserNotFoundException;
}
