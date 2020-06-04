package com.derandecker.bakingapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.derandecker.bakingapp.model.Recipe;
import com.derandecker.bakingapp.model.RecipeWithIngredients;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert
    void insertRecipes(List<Recipe> recipes);

    @Query("SELECT name AND servings FROM recipes")
    LiveData<List<Recipe>> loadRecipeNamesAndServings();

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipeId = :id")
    public LiveData<List<RecipeWithIngredients>> loadRecipeIngredients(int id);

}
