package com.haruhanjan.recipeservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecipeProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_process_id")
    private Long id;

    private Integer cookingOrder;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Builder
    public RecipeProcess(Integer cookingOrder, String description, Recipe recipe) {
        this.cookingOrder = cookingOrder;
        this.description = description;
        this.recipe = recipe;
    }


}
