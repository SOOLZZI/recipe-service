package com.haruhanjan.recipeservice.repository;

import com.haruhanjan.recipeservice.entity.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository {

    Recipe save(Recipe recipe);
    Optional<Recipe> findById(Long id);
//    Optional<Recipe> findByRecipe(String recipe);
    List<Recipe> findAll();

}
