package com.haruhanjan.recipeservice.dto.recipeProcess;

import com.haruhanjan.recipeservice.entity.RecipeProcess;
import lombok.Getter;

@Getter
public class RecipeProcessResponseDTO {
    private Integer cookingOrder;
    private String description;

    public RecipeProcessResponseDTO(RecipeProcess entity) {
        this.cookingOrder = entity.getCookingOrder();
        this.description = entity.getDescription();
    }
}
