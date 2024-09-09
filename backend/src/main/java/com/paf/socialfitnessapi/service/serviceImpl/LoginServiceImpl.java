package com.paf.socialfitnessapi.service.serviceImpl;

import com.paf.socialfitnessapi.controller.request.UserLoginRequest;
import com.paf.socialfitnessapi.controller.response.AuthenticationResponse;
import com.paf.socialfitnessapi.model.User;
import com.paf.socialfitnessapi.repository.LoginRepository;
import com.paf.socialfitnessapi.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {
    private LoginRepository loginRepository;
    @Override
    public AuthenticationResponse authenticateUser(UserLoginRequest userLoginRequest) {
        Optional<User> userOptional = loginRepository.findByEmailAndPassword(userLoginRequest.getEmail(), userLoginRequest.getPassword());

        if(!userOptional.isPresent()){
            return new AuthenticationResponse(null,false);
        }

        User user = userOptional.get();
        return new AuthenticationResponse(user.getId(), true);
    }
}
