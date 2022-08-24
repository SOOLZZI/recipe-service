package com.haruhanjan.recipeservice.dto;

import com.haruhanjan.recipeservice.entity.BaseTimeEntity;
import com.haruhanjan.recipeservice.entity.Recipe;
import com.haruhanjan.recipeservice.entity.RecipeIngredient;
import com.haruhanjan.recipeservice.entity.RecipeProcess;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeRequestDTO {

    @Getter
    private String title;
    private String description;
    private String writer;
    private List<RecipeIngredient> ingredients;
    private List<RecipeProcess> processes;
    private LocalTime cookingTime;
    private BaseTimeEntity baseTimeEntity;

    public Recipe toEntity(){
        return Recipe.builder()
                .title(title)
                .description(description)
                .writer(writer)
                .ingredients(ingredients)
                .processes(processes)
                .cookingTime(cookingTime)
                .baseTimeEntity(baseTimeEntity)
                .build();
    }

}
