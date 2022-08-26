package com.haruhanjan.recipeservice.dto;

import com.haruhanjan.recipeservice.entity.Ingredient;
import com.haruhanjan.recipeservice.entity.IngredientType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
public class CreateIngredientRequestDTO {

    private String name;
    private IngredientType ingredientType;

    public Ingredient toEntity() {
        return Ingredient.builder()
                .name(name)
                .ingredientType(ingredientType)
                .build();
    }
}
