package com.paf.socialfitnessapi.service.serviceImpl;

import com.paf.socialfitnessapi.controller.request.UserRequest;
import com.paf.socialfitnessapi.controller.response.UserIdResponse;
import com.paf.socialfitnessapi.controller.response.UserResponse;
import com.paf.socialfitnessapi.exception.UserNotFoundException;
import com.paf.socialfitnessapi.model.User;
import com.paf.socialfitnessapi.repository.UserRepository;
import com.paf.socialfitnessapi.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;
    @Override
    public ResponseEntity<Object> createUser(UserRequest userRequest){
        if(userRequest.getSource()==null){
            User user = modelMapper.map(userRequest,User.class);
            user.setCreatedDate(LocalDate.now());
            user.setCreatedTime(LocalTime.now());

            userRepository.save(user);
        }

        if(Objects.equals(userRequest.getSource(), "google")){
            System.out.println("User Google");
            String email = userRequest.getEmail();
            if(userRepository.existsByEmail(email)){
                User user = userRepository.findByEmail(email);
                //send user response
            }

            User googleUser = new User();
            googleUser.setFirstName(userRequest.getFirstName());
//            googleUser.setLastName(userRequest.getLastName());
            googleUser.setEmail(userRequest.getEmail());
            googleUser.setPassword(userRequest.getPassword());

            try {
                userRepository.save(googleUser);
            }catch (DataIntegrityViolationException e){
                return new ResponseEntity<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return null;

    }

    @Override
    public UserResponse getUser(Long id)throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
                ()->new UserNotFoundException("User Not Found with id "+id)
        );

        UserResponse userResponse = modelMapper.map(user,UserResponse.class);
        userResponse.setNoOfDays((int) ChronoUnit.DAYS.between(user.getCreatedDate(),LocalDate.now()));

        userResponse.setPassword("xxxxxxxx");

        return userResponse;
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
                ()->new UserNotFoundException("User Not Found with id "+id)
        );

        user.setFirstName(userRequest.getFirstName());
        user.setPassword(userRequest.getPassword());

        userRepository.save(user);

        UserResponse userResponse = modelMapper.map(user,UserResponse.class);
        return userResponse;
    }

    @Override
    public UserIdResponse deleteUser(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id).orElseThrow(
                ()->new UserNotFoundException("User Not found with id "+id)
        );

        userRepository.deleteById(id);
        return UserIdResponse.builder()
                .msg(user.getFirstName()+" Deleted")
                .build();
    }
}
