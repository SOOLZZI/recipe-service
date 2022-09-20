package com.haruhanjan.recipeservice.sample;

import com.haruhanjan.recipeservice.dto.ingredient.IngredientRequestDTO;
import com.haruhanjan.recipeservice.dto.ingredient.IngredientResponseDTO;
import com.haruhanjan.recipeservice.entity.Ingredient;
import com.haruhanjan.recipeservice.entity.IngredientType;

import java.util.Random;

public class IngredientSample {

    Random random = new Random();

    public Ingredient getIngredient() {
        Ingredient ingredient = new Ingredient("양파", IngredientType.vegetable);
        ingredient.setId(random.nextLong());

        return ingredient;
    }

    public IngredientResponseDTO getIngredientResponseDTO() {
        return new IngredientResponseDTO(getIngredient());
    }

    public IngredientRequestDTO getIngredientRequestDTO() {
        return new IngredientRequestDTO("감자", IngredientType.vegetable);
    }
}
