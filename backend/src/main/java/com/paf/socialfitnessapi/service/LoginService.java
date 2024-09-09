package com.paf.socialfitnessapi.service;

import com.paf.socialfitnessapi.controller.request.UserLoginRequest;
import com.paf.socialfitnessapi.controller.response.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    AuthenticationResponse authenticateUser(UserLoginRequest userLoginRequest);
}
