package com.paf.socialfitnessapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String mealType;
    private String recipes;
    private String ingredients;
    private String nutrition;
    private String portion;
    private String instructions;

    @Lob
    @Column(name = "mealImage", columnDefinition = "LONGBLOB")
    private byte[] imageData;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "meal")
    private List<MealLike> mealLikeList;


}
