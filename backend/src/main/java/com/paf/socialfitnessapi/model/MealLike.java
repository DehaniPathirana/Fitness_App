package com.paf.socialfitnessapi.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class MealLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean status;

    @ManyToOne
    private User likedUser;

    @ManyToOne
    private Meal meal;

    @ManyToOne
    private User postOwner;
}
