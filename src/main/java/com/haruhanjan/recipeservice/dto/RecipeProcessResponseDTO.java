package com.haruhanjan.recipeservice.dto;

import com.haruhanjan.recipeservice.entity.RecipeProcess;
import lombok.Getter;

@Getter
public class RecipeProcessResponseDTO {
    private Integer cookingOrder;
    private String discription;

    public RecipeProcessResponseDTO(RecipeProcess entity) {
        this.cookingOrder = entity.getCookingOrder();
        this.discription = entity.getDiscription();
    }
}
