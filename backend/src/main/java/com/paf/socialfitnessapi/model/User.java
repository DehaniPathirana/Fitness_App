package com.paf.socialfitnessapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String imageUrl;
    private LocalDate createdDate;
    private LocalTime createdTime;
    private String source;

    @OneToMany(mappedBy = "user")
    private List<Post> postList;

    @OneToMany(mappedBy = "user")
    private List<WorkoutStatus> workoutStatusList;

    @OneToMany(mappedBy = "user")
    private List<Meal> mealList;

    @OneToMany(mappedBy = "likedUser")
    private List<MealLike> mealLikeList;

    @OneToMany(mappedBy = "postOwner")
    private List<MealLike> mealLikeList1;
}
