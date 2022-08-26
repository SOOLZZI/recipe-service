package com.haruhanjan.recipeservice.dto;

import com.haruhanjan.recipeservice.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeRequestDTO {

    private String title;
    private String description;
    private String writer;
    private Ingredient ingredient;
    private List<RecipeProcess> processes;
    private LocalTime cookingTime;
    private BaseTimeEntity baseTimeEntity;

    public Recipe toEntity(){
        return Recipe.builder()
                .title(title)
                .description(description)
                .writer(writer)
                .ingredients(ingredient.getName())
                .processes(processes)
                .cookingTime(cookingTime)
                .baseTimeEntity(baseTimeEntity)
                .build();
    }
}
