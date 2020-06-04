package com.derandecker.bakingapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Recipes")
public class Recipe {
    @PrimaryKey
    public int recipeId;
    public String name;
    public int servings;


    @Ignore
    public Recipe() {
    }

    public Recipe(int id, String name, int servings) {
        this.recipeId = id;
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

