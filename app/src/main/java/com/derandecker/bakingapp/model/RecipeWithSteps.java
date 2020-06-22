package com.derandecker.bakingapp.model;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithSteps {
    @Embedded public Recipe recipe;
    @Relation(
            //parentColumn is in Recipe.class
            parentColumn = "recipeId",
            //entityColumn is in Steps.class
            entityColumn = "recipeId"
    )

    public List<Steps> steps;

    public RecipeWithSteps(){}
}
