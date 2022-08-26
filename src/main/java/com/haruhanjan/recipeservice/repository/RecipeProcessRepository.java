package com.haruhanjan.recipeservice.repository;

import com.haruhanjan.recipeservice.entity.RecipeProcess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeProcessRepository extends JpaRepository<RecipeProcess, Long> {
}
