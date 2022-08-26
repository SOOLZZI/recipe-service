package com.haruhanjan.recipeservice.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private IngredientType ingredientType;

    @Builder
    public Ingredient(String name, IngredientType ingredientType) {
        this.name = name;
        this.ingredientType = ingredientType;
    }
}
