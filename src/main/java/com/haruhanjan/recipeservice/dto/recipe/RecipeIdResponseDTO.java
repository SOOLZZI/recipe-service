package com.haruhanjan.recipeservice.dto.recipe;

import com.haruhanjan.recipeservice.entity.Recipe;
import lombok.Getter;

@Getter
public class RecipeIdResponseDTO {
    private Long id;

    public RecipeIdResponseDTO(Recipe entity) {
        this.id = entity.getId();
    }

    public RecipeIdResponseDTO(Long id) {
        this.id = id;
    }
}
