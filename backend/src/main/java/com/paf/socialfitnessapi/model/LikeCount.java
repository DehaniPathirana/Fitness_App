package com.paf.socialfitnessapi.model;

import jakarta.persistence.Entity;
import lombok.Data;


public interface LikeCount {
    Long getMealId();
    Long getLikeCount();

}
