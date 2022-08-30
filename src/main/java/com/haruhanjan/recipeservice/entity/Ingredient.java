package com.haruhanjan.recipeservice.entity;


import com.haruhanjan.recipeservice.dto.ingredient.ModifyIngredientRequestDto;
import com.haruhanjan.recipeservice.dto.recipe.ModifyRecipeRequsetDTO;
import lombok.*;

import javax.persistence.*;

import static java.util.Optional.ofNullable;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private Long id;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private IngredientType ingredientType;

    @Builder
    public Ingredient(String name, IngredientType ingredientType) {
        this.name = name;
        this.ingredientType = ingredientType;
    }

    @Embedded
    @Builder.Default
    private BaseTimeEntity baseTimeEntity = new BaseTimeEntity();

    public void modify(ModifyIngredientRequestDto dto) {
        ofNullable(dto.getName()).ifPresent(n -> this.name = n);
        ofNullable(dto.getIngredientType()).ifPresent(it -> this.ingredientType = it);
        baseTimeEntity.update();
    }

    public void delete() {
        baseTimeEntity.delete();
    }
}
