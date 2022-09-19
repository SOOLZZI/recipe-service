package com.haruhanjan.recipeservice.controller;

import com.haruhanjan.recipeservice.dto.recipe.RecipeIdResponseDTO;
import com.haruhanjan.recipeservice.dto.recipe.RecipeRequestDTO;
import com.haruhanjan.recipeservice.dto.recipe.RecipeResponseDTO;
import com.haruhanjan.recipeservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<RecipeResponseDTO>> readAll(){
        List<RecipeResponseDTO> result = recipeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<RecipeIdResponseDTO> create(@RequestBody @Valid RecipeRequestDTO dto) {
        Long result = recipeService.save(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RecipeIdResponseDTO(result));
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> put(@PathVariable Long id,
                                      @RequestBody @Valid RecipeRequestDTO dto) {
        recipeService.modify(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recipeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}