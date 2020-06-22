package com.derandecker.bakingapp.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithIngredients {
    @Embedded public Recipe recipe;
    @Relation(
            //parentColumn is in Recipe.class
            parentColumn = "recipeId",
            //entityColumn is in Ingredients.class
            entityColumn = "recipeId",
            entity = Ingredients.class
    )
    public List<Ingredients> ingredients;

    public RecipeWithIngredients(){}
}
