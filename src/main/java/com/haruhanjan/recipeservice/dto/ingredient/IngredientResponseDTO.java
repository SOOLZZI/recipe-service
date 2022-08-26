package com.haruhanjan.recipeservice.dto.ingredient;

import com.haruhanjan.recipeservice.entity.Ingredient;

public class IngredientResponseDTO {
    private Long id;
    private String name;
    private String type;

    public IngredientResponseDTO(Ingredient entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.type = entity.getIngredientType().name();
    }
}
