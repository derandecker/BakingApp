package com.derandecker.bakingapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithIngredients {
    @Embedded public Recipe recipe;
    @Relation(
            parentColumn = "recipeId",
            entityColumn = "ingredientsId"
    )
    public List<Ingredients> ingredients;
}