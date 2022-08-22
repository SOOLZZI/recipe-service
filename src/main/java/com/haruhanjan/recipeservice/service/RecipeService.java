package com.haruhanjan.recipeservice.service;

import com.haruhanjan.recipeservice.domain.Recipe;
import com.haruhanjan.recipeservice.repository.RecipeRepository;

import javax.transaction.Transactional;
import java.nio.channels.MembershipKey;
import java.util.List;
import java.util.Optional;

@Transactional
public class RecipeService {
    private final RecipeRepository recipeRepository;
    public RecipeService(RecipeRepository recipeRepository) {this.recipeRepository = recipeRepository;}

    // 레시피 작성
    public Long write(Recipe recipe){
        recipeRepository.save(recipe);
        return recipe.getId();
    }

    // 전체 레시피 조회
    public List<Recipe> findRecipes() {return recipeRepository.findAll();}
    public Optional<Recipe> findOne(Long memberId) {
        return recipeRepository.findById(memberId);
    }

}
