package com.derandecker.bakingapp.utils;

import android.util.JsonReader;

import com.derandecker.bakingapp.model.Ingredients;
import com.derandecker.bakingapp.model.Recipe;
import com.derandecker.bakingapp.model.Steps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {

    public static List<Recipe> parseRecipesJson(String json) throws JSONException {

        JSONArray recipesJson = new JSONArray(json);
        Recipe currentRecipe;
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        for (int i = 0; i < recipesJson.length(); i++) {
            JSONObject recipe = recipesJson.getJSONObject(i);
            int id = recipe.getInt("id");
            String name = recipe.getString("name");
            int servings = recipe.getInt("servings");

            currentRecipe = new Recipe(id, name, servings);
            recipes.add(currentRecipe);
        }
        return recipes;
    }

    public static List<Ingredients> parseIngredientsJson(String json, Integer recipeId) throws JSONException {

        JSONArray recipesJson = new JSONArray(json);
        int index = recipeId - 1;
        JSONObject recipe = recipesJson.getJSONObject(index);
        JSONArray ingredientsArray = recipe.getJSONArray("ingredients");

        Ingredients currentIngredient;
        ArrayList<Ingredients> ingredients = new ArrayList<Ingredients>();

        for (int i = 0; i < ingredientsArray.length(); i++){
            JSONObject ingredient = ingredientsArray.getJSONObject(i);
            int quantity = ingredient.getInt("quantity");
            String measure = ingredient.getString("measure");
            String name = ingredient.getString("ingredient");

            currentIngredient = new Ingredients(recipeId, quantity, measure, name);
            ingredients.add(currentIngredient);
        }
        return ingredients;
    }

    public static List<Steps> parseStepsJson(String json, Integer recipeId) throws JSONException {
        JSONArray recipesJson = new JSONArray(json);
        int index = recipeId - 1;
        JSONObject recipe = recipesJson.getJSONObject(index);
        JSONArray stepsArray = recipe.getJSONArray("steps");

        Steps currentStep;
        ArrayList<Steps> steps = new ArrayList<Steps>();

        for (int i = 0; i < stepsArray.length(); i++) {
            JSONObject ingredient = stepsArray.getJSONObject(i);
            int stepId = ingredient.getInt("id");
            String shortDescription = ingredient.getString("shortDescription");
            String description = ingredient.getString("description");
            String url = ingredient.getString("videoURL");

            currentStep = new Steps(stepId, recipeId, shortDescription, description, url);
            steps.add(currentStep);
        }
        return steps;
    }
}