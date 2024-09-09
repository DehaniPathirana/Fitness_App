package com.paf.socialfitnessapi.controller.request;

import lombok.Data;

@Data
public class MealLikeRequest {
    private Boolean status;
    private Long likeOwner;
    private Long postOwner;

}
