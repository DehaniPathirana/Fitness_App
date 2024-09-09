package com.paf.socialfitnessapi.controller.request;

import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String avatar;
    private String source;
}
