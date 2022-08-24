package com.haruhanjan.recipeservice.dto;

import com.haruhanjan.recipeservice.entity.BaseTimeEntity;
import com.haruhanjan.recipeservice.entity.RecipeIngredient;
import com.haruhanjan.recipeservice.entity.RecipeProcess;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyRecipeRequsetDTO {

    private String title;
    private String description;
    private String writer;
    private List<RecipeIngredient> ingredients;
    private List<RecipeProcess> processes;
    private LocalTime cookingTime;
    private BaseTimeEntity baseTimeEntity;

}
