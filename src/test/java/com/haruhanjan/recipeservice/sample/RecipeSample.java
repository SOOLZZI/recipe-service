package com.haruhanjan.recipeservice.sample;

import com.haruhanjan.recipeservice.dto.recipe.RecipeRequestDTO;
import com.haruhanjan.recipeservice.dto.recipe.RecipeResponseDTO;
import com.haruhanjan.recipeservice.entity.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecipeSample {

    Random random = new Random();

    public RecipeResponseDTO getRecipeResponseDTO() {
        Recipe recipe = getRecipe();
        List<RecipeIngredient> ingredients = new ArrayList<>();
        List<RecipeProcess> processes = new ArrayList<>();

        ingredients.add(new RecipeIngredient(recipe, getIngredient()));
        ingredients.add(new RecipeIngredient(recipe, getIngredient()));
        ingredients.add(new RecipeIngredient(recipe, getIngredient()));

        processes.add(getRecipeProcess(recipe));
        processes.add(getRecipeProcess(recipe));
        processes.add(getRecipeProcess(recipe));

        recipe.setRecipeIngredients(ingredients);
        recipe.setRecipeProcesses(processes);

        return new RecipeResponseDTO(recipe);
    }

    public Recipe getRecipe() {
        return Recipe.builder()
                .id(random.nextLong())
                .title("sample" + random.nextInt(10))
                .description("맛있어용")
                .writer("현준")
                .cookingTime(LocalTime.now())
                .build();
    }

    public Ingredient getIngredient() {
        Ingredient ingredient = Ingredient.builder()
                .name("야채야채"+ random.nextInt(10))
                .ingredientType(IngredientType.vegetable)
                .build();
        ingredient.setId(random.nextLong());

        return ingredient;
    }

    public RecipeProcess getRecipeProcess(Recipe recipe) {
        RecipeProcess process = RecipeProcess.builder()
                .recipe(recipe)
                .cookingOrder(random.nextInt())
                .description("썰어넣으세용")
                .build();
        process.setId(random.nextLong());

        return process;
    }

    public RecipeRequestDTO getRecipeRequestDTO() {
        List<Long> ingredients = new ArrayList<>();
        ingredients.add(getIngredient().getId());
        ingredients.add(getIngredient().getId());
        ingredients.add(getIngredient().getId());

        List<String> processes = new ArrayList<>();
        processes.add("프로세스1");
        processes.add("프로세스2");
        processes.add("프로세스3");


        return new RecipeRequestDTO("타이틀", "설명", "작성자", ingredients, processes, LocalTime.now());

    }
}
