package com.haruhanjan.recipeservice.controller;

import com.haruhanjan.recipeservice.dto.ingredient.CreateIngredientRequestDTO;
import com.haruhanjan.recipeservice.dto.ingredient.IngredientResponseDTO;
import com.haruhanjan.recipeservice.dto.ingredient.ModifyIngredientRequestDto;
import com.haruhanjan.recipeservice.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<List<IngredientResponseDTO>> readAll(){
        List<IngredientResponseDTO> result = ingredientService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody @Valid CreateIngredientRequestDTO dto) {
        Long result = ingredientService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patch(@PathVariable Long id,
                                      @RequestBody ModifyIngredientRequestDto dto) {
        ingredientService.modify(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ingredientService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
