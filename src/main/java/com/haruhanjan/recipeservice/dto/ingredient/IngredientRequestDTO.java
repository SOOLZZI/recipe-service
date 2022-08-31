package com.haruhanjan.recipeservice.dto.ingredient;

import com.haruhanjan.recipeservice.entity.Ingredient;
import com.haruhanjan.recipeservice.entity.IngredientType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class IngredientRequestDTO {

    @NotNull
    private String name;

    @NotNull
    private IngredientType ingredientType;

    public Ingredient toEntity() {
        return Ingredient.builder()
                .name(name)
                .ingredientType(ingredientType)
                .build();
    }
}
