package com.haruhanjan.recipeservice.dto.recipe;

import com.haruhanjan.recipeservice.dto.recipeIngredient.RecipeIngredientResponseDTO;
import com.haruhanjan.recipeservice.dto.recipeProcess.RecipeProcessResponseDTO;
import com.haruhanjan.recipeservice.entity.BaseTimeEntity;
import com.haruhanjan.recipeservice.entity.Recipe;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class RecipeResponseDTO {
    private Long id; // 고객이 정하는게 아니라 시스템이 정하는 id

    private String title;
    private String description;
    private String writer;

    private List<RecipeIngredientResponseDTO> ingredients;
    private List<RecipeProcessResponseDTO> processes;

    private LocalTime cookingTime;

    public RecipeResponseDTO(Recipe entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.writer = entity.getWriter();

        this.ingredients = entity.getRecipeIngredients().stream()
                .map(RecipeIngredientResponseDTO::new)
                .collect(Collectors.toList());

        this.processes = entity.getRecipeProcesses().stream()
                .map(RecipeProcessResponseDTO::new)
                .collect(Collectors.toList());

        this.cookingTime = entity.getCookingTime();
    }
}