package com.haruhanjan.recipeservice.entity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id; // 고객이 정하는게 아니라 시스템이 정하는 id

    private String title;

    private String description;

    private String writer;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredient> ingredients = new ArrayList<>();

    @OneToMany(mappedBy ="recipe")
    private List<RecipeProcess> processes = new ArrayList<>();

    private LocalTime cookingTime;

    @Embedded
    @Builder.Default
    private BaseTimeEntity baseTimeEntity = new BaseTimeEntity();

}