package com.haruhanjan.recipeservice.entity;
import com.haruhanjan.recipeservice.dto.recipe.RecipeRequestDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id; // 고객이 정하는게 아니라 시스템이 정하는 id

    private String title;

    private String description;

    private String writer;

    @OneToMany(mappedBy = "recipe", fetch=FetchType.LAZY)
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

    @OneToMany(mappedBy ="recipe", fetch=FetchType.LAZY)
    private List<RecipeProcess> recipeProcesses = new ArrayList<>();

    private LocalTime cookingTime;

    @Embedded
    @Builder.Default
    private BaseTimeEntity baseTimeEntity = new BaseTimeEntity();

    public void modify(RecipeRequestDTO dto) {
        this.title = dto.getTitle();
        this.description = dto.getDescription();
        this.cookingTime = dto.getCookingTime();
        baseTimeEntity.update();
    }

    public void delete() {
        baseTimeEntity.delete();
    }
}