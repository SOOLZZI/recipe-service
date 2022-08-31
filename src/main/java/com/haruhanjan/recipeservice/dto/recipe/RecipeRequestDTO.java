package com.haruhanjan.recipeservice.dto.recipe;

import com.haruhanjan.recipeservice.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequestDTO {

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String writer;

    @NotNull
    private List<Long> ingredients;
    @NotNull
    private List<String> processes;
    @NotNull
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
