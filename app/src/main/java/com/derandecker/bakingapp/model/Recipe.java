package com.derandecker.bakingapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "Recipes")
public class Recipe {
    @PrimaryKey
    public int recipeId;
    public String name;


    @Ignore
    public Recipe() {
    }

    public Recipe(int id, String name) {
        this.recipeId = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return recipeId;
    }
}

