package com.haruhanjan.recipeservice.dto.ingredient;

import lombok.Getter;

@Getter
public class IngredientIdResponseDTO {
    Long id;

    public IngredientIdResponseDTO(Long id) {
        this.id = id;
    }
}
