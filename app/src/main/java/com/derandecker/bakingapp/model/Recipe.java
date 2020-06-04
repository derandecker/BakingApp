package com.derandecker.bakingapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
public class Recipe {
    @PrimaryKey
    public int recipeId;
    public String name;
    public int servings;

    @Ignore
    public Recipe() {
    }

    public Recipe(int recipeId, String name, int servings) {
        this.recipeId = recipeId;
        this.name = name;
        this.servings = servings;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return recipeId;
    }

    public int getServings(){
        return servings;
    }
}

