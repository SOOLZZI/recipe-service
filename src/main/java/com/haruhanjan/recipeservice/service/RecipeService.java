package com.haruhanjan.recipeservice.service;

import com.haruhanjan.recipeservice.dto.CreateRecipeRequestDTO;
import com.haruhanjan.recipeservice.dto.RecipeResponseDTO;
import com.haruhanjan.recipeservice.entity.Recipe;
import com.haruhanjan.recipeservice.repository.IngredientRepository;
import com.haruhanjan.recipeservice.repository.RecipeProcessRepository;
import com.haruhanjan.recipeservice.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeProcessRepository recipeProcessRepository;

    // 레시피 작성
    public Long create(CreateRecipeRequestDTO dto){
        Recipe recipe = dto.toEntity();
        // RecipeIngredient 생성 TODO
        dto.getIngredients()
                .stream()
                .map(ingredientRepository::findById)
                .map(i -> i.orElseThrow(EntityNotFoundException::new))
                .forEach(i -> recipe.getIngredients().add(i));


        //recipe.getIngredients()
        // RecipeProcess 생성 TODO

        dto.getProcesses()
                .stream()
                .map(recipeProcessRepository::findById)


        recipeRepository.save(recipe);
        return recipe.getId();
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

}
