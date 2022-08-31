package com.haruhanjan.recipeservice.service;

import com.haruhanjan.recipeservice.dto.recipe.RecipeRequestDTO;
import com.haruhanjan.recipeservice.dto.recipe.RecipeResponseDTO;
import com.haruhanjan.recipeservice.entity.Recipe;
import com.haruhanjan.recipeservice.entity.RecipeIngredient;
import com.haruhanjan.recipeservice.entity.RecipeProcess;
import com.haruhanjan.recipeservice.repository.IngredientRepository;
import com.haruhanjan.recipeservice.repository.RecipeIngredientRepository;
import com.haruhanjan.recipeservice.repository.RecipeProcessRepository;
import com.haruhanjan.recipeservice.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;


import static java.util.stream.IntStream.*;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeProcessRepository recipeProcessRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    // 레시피 작성
    public Long save(RecipeRequestDTO dto){
        Recipe recipe = dto.toEntity();

        recipeRepository.save(recipe);

        saveRecipeIngredient(dto.getIngredients(), recipe);
        saveRecipeProcess(dto.getProcesses(), recipe);

        return recipe.getId();
    }

    private void saveRecipeProcess(List<String> processes, Recipe recipe) {
        range(0,processes.size())
                .mapToObj(i->
                        new RecipeProcess(i+1, processes.get(i), recipe))
                .forEach(recipeProcessRepository::save);
    }

    private void saveRecipeIngredient(List<Long> ingredients, Recipe recipe) {
        ingredients.stream()
                .map(ingredientRepository::findById)
                .map(i -> i.orElseThrow(EntityNotFoundException::new))
                .map(i -> new RecipeIngredient(recipe, i))
                .forEach(recipeIngredientRepository::save);
    }

    // 전체 레시피 조회
    public List<RecipeResponseDTO> findAll() {
        return recipeRepository.findAll()
                .stream().map(RecipeResponseDTO::new)
                .collect(Collectors.toList());
    }

    public RecipeResponseDTO findById(Long id) {
        return recipeRepository.findById(id)
                .stream().map(RecipeResponseDTO::new)
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void deleteById(Long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        recipe.delete();
    }

    @Transactional
    public void modify(Long id, RecipeRequestDTO dto) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        recipe.modify(dto);
    }
}
