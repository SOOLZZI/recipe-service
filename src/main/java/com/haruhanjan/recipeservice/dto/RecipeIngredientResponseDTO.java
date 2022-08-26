package com.haruhanjan.recipeservice.dto;

import com.haruhanjan.recipeservice.entity.RecipeIngredient;
import lombok.Getter;

@Getter
public class RecipeIngredientResponseDTO {
    private Long id;
    private String name;

    public RecipeIngredientResponseDTO(RecipeIngredient entity) {
        this.id = entity.getIngredient().getId();
        this.name = entity.getIngredient().getName();
    }
}
