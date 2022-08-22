package com.haruhanjan.recipeservice.controller;

import com.haruhanjan.recipeservice.domain.Recipe;
import com.haruhanjan.recipeservice.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/recipes/new")
    public String createForm(){
        return "recipes/createMemberForm";
    }

    @PostMapping("/recipes/new")
    public String create(RecipeForm form){
        Recipe recipe = new Recipe();
        recipe.setRecipe(form.getRecipe());

        recipeService.write(recipe);

        return "redirect:/";
    }

    @GetMapping("/recipes")
    public String list(Model model){
        List<Recipe> members = recipeService.findRecipes();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
