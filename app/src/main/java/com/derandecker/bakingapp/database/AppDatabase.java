package com.derandecker.bakingapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.derandecker.bakingapp.model.Ingredients;
import com.derandecker.bakingapp.model.Recipe;
import com.derandecker.bakingapp.model.RecipeWithIngredients;
import com.derandecker.bakingapp.model.RecipeWithSteps;
import com.derandecker.bakingapp.model.Steps;

@Database(entities = {Recipe.class, Ingredients.class, Steps.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "recipes";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        return sInstance;
    }

    public abstract RecipeDao RecipeDao();

}
