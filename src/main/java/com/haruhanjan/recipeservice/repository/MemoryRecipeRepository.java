package com.haruhanjan.recipeservice.repository;

import com.haruhanjan.recipeservice.domain.Recipe;

import java.util.*;

public class MemoryRecipeRepository implements RecipeRepository{

    private static Map<Long,Recipe> recipe = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Recipe save(Recipe recipe) {
        return null;
    }

    @Override
    public Optional<Recipe> findById(Long id) {
        return Optional.ofNullable(recipe.get(id));
    }

//    @Override
//    public Optional<Recipe> findByRecipe(String recipe) {
//        return recipe.values().stream().ilter()
//    }

    @Override
    public List<Recipe> findAll() {
        return new ArrayList<>(recipe.values());
    }
}
