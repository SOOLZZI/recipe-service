package com.haruhanjan.recipeservice.repository;

import com.haruhanjan.recipeservice.entity.Ingredient;
import com.haruhanjan.recipeservice.entity.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByIngredientType(IngredientType ingredientType);
}
