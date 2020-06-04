package com.derandecker.bakingapp.utils;

import com.derandecker.bakingapp.model.Recipe;

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

}