package com.paf.socialfitnessapi.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private Long userid;
    private Boolean authenticated;


}
