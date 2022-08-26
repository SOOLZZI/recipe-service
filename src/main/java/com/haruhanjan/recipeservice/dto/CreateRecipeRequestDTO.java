package com.haruhanjan.recipeservice.dto;

import com.haruhanjan.recipeservice.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeRequestDTO {

    private String title;
    private String description;
    private String writer;

    @Getter
    private List<Long> ingredients;
    @Getter
    private List<String> processes;
    private LocalTime cookingTime;

    public Recipe toEntity(){
        return Recipe.builder()
                .title(title)
                .description(description)
                .writer(writer)
                .cookingTime(cookingTime)
                .build();
    }
}
