package com.haruhanjan.recipeservice.dto.ingredient;

import com.haruhanjan.recipeservice.entity.BaseTimeEntity;
import com.haruhanjan.recipeservice.entity.IngredientType;
import com.haruhanjan.recipeservice.entity.RecipeIngredient;
import com.haruhanjan.recipeservice.entity.RecipeProcess;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModifyIngredientRequestDto {
        private String name;
        private IngredientType ingredientType;
}
