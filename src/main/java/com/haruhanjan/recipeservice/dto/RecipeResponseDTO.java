package com.haruhanjan.recipeservice.dto;

import com.haruhanjan.recipeservice.entity.BaseTimeEntity;
import com.haruhanjan.recipeservice.entity.Recipe;
import com.haruhanjan.recipeservice.entity.RecipeIngredient;
import com.haruhanjan.recipeservice.entity.RecipeProcess;
import com.haruhanjan.recipeservice.repository.RecipeRepository;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private BaseTimeEntity baseTimeEntity;

    public RecipeResponseDTO(Recipe entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.title = entity.getTitle();
        this.description = entity.getDescription();
        this.writer = entity.getWriter();

        this.ingredients = entity.getIngredients().stream()
                .map(RecipeIngredientResponseDTO::new)
                .collect(Collectors.toList());

        this.processes = entity.getProcesses().stream()
                .map(RecipeProcessResponseDTO::new)
                .collect(Collectors.toList());

        this.cookingTime = entity.getCookingTime();
        this.baseTimeEntity = entity.getBaseTimeEntity();
    }
}