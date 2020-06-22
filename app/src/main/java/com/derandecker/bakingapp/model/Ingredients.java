package com.derandecker.bakingapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredients")
public class Ingredients {
    @PrimaryKey(autoGenerate = true)
    public int ingredientsId;
    public int recipeId;
    public int quantity;
    public String measure;
    public String ingredient;

    @Ignore
    public Ingredients() {
    }

    public Ingredients(int ingredientsId, int recipeId, int quantity,
                       String measure, String ingredient) {
        this.ingredientsId = ingredientsId;
        this.recipeId = recipeId;
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    @Ignore
    public Ingredients(int recipeId, int quantity, String measure, String ingredient){
        this.recipeId = recipeId;
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

}
