package com.haruhanjan.recipeservice.service;

import com.haruhanjan.recipeservice.dto.ingredient.CreateIngredientRequestDTO;
import com.haruhanjan.recipeservice.dto.ingredient.IngredientResponseDTO;
import com.haruhanjan.recipeservice.dto.ingredient.ModifyIngredientRequestDto;
import com.haruhanjan.recipeservice.dto.recipe.ModifyRecipeRequsetDTO;
import com.haruhanjan.recipeservice.entity.Ingredient;
import com.haruhanjan.recipeservice.entity.IngredientType;
import com.haruhanjan.recipeservice.entity.Recipe;
import com.haruhanjan.recipeservice.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;

    // 재료 생성
    public Long create(CreateIngredientRequestDTO dto){
        Ingredient ingredient = dto.toEntity();
        ingredientRepository.save(ingredient);
        return ingredient.getId();
    }

    // 재료 전체 조회
    public List<IngredientResponseDTO> findAll(){
        return ingredientRepository.findAll()
                .stream().map(IngredientResponseDTO::new)
                .collect(Collectors.toList());
    }

    // 재료 id로 조회
    public IngredientResponseDTO findById(Long id){
        return ingredientRepository.findById(id)
                .stream().map(IngredientResponseDTO::new)
                .findFirst()
                .orElseThrow(EntityNotFoundException::new);
    }

    // 재료 타입별 조회
    public List<IngredientResponseDTO> findAllByIngredientType(IngredientType type){
        return ingredientRepository.findAllByIngredientType(type)
                .stream().map(IngredientResponseDTO::new)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ingredient.delete();
    }

    public void modify(Long id, ModifyIngredientRequestDto dto) {
        Ingredient ingredient = ingredientRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        ingredient.modify(dto);
    }
}
