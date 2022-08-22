package com.haruhanjan.recipeservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RecipeProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_process_id")
    private Long id;

    private Integer cookingOrder;

    private String discription;

    @ManyToOne(fetch = FetchType.LAZY)
    private Recipe recipe;

}
