package com.derandecker.bakingapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.derandecker.bakingapp.model.Ingredients;
import com.derandecker.bakingapp.model.Recipe;
import com.derandecker.bakingapp.model.RecipeWithIngredients;
import com.derandecker.bakingapp.model.RecipeWithSteps;
import com.derandecker.bakingapp.model.Steps;

import java.util.List;

@Dao
public interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertRecipeNamesAndServings(List<Recipe> recipes);

    @Insert
    void insertIngredients(List<Ingredients> ingredients);

    @Insert
    void insertSteps(List<Steps> steps);

    //testing purposes
    @Query("SELECT * FROM recipes WHERE recipeId = :id")
    Recipe loadRecipeById(int id);


    @Query("SELECT * FROM recipes")
//    LiveData<List<Recipe>> loadRecipeNamesAndServings();
    List<Recipe> loadRecipeNamesAndServings();

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipeId = :id")
//    LiveData<List<RecipeWithIngredients>> loadRecipeIngredients(int id);
    RecipeWithIngredients loadRecipeIngredients(int id);

    @Transaction
    @Query("SELECT * FROM recipes WHERE recipeId = :id")
//    LiveData<List<RecipeWithSteps>> loadRecipeSteps(int id);
    RecipeWithSteps loadRecipeSteps(int id);

}
