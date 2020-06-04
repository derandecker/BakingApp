package com.derandecker.bakingapp.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeWithSteps {
    @Embedded public Recipe recipe;
    @Relation(
            parentColumn = "recipeId",
            entityColumn = "stepId"
    )
    public List<Steps> steps;
}
