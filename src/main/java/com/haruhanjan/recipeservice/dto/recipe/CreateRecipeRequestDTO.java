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
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecipeRequestDTO {

    @NotNull
    private String title;
    @NotNull
    private String description;
    @NotNull
    private String writer;

    @Getter
    @NotNull
    private List<Long> ingredients;
    @Getter
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
