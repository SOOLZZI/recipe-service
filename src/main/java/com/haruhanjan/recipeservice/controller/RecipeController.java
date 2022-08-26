package com.haruhanjan.recipeservice.controller;

import com.haruhanjan.recipeservice.dto.recipe.CreateRecipeRequestDTO;
import com.haruhanjan.recipeservice.dto.recipe.RecipeResponseDTO;
import com.haruhanjan.recipeservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CreateRecipeRequestDTO dto){
        Long result = recipeService.save(dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<RecipeResponseDTO>> findAll( ){
        return ResponseEntity.ok(recipeService.findAll());
    }

}
