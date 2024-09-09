package com.paf.socialfitnessapi.controller.response;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
//    private byte[] avatar;
    private LocalDate createdDate;
    private Integer noOfDays;
}
