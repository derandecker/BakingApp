package com.derandecker.bakingapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.android.material.button.MaterialButton;

@Entity(tableName = "steps")
public class Steps {
    @PrimaryKey(autoGenerate = true)
    public int stepId;
    public int recipeId;
    public int stepNumber;
    public String shortDescription;
    public String description;
    public String videoUrl;

    @Ignore
    public Steps(){
    }

    public Steps(int stepId, int recipeId, int stepNumber, String shortDescription,
                 String description, String videoUrl){
        this.stepId = stepId;
        this.recipeId = recipeId;
        this.stepNumber = stepNumber;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
    }

    @Ignore
    public Steps(int recipeId, int stepNumber, String shortDescription,
                 String description, String videoUrl){
        this.recipeId = recipeId;
        this.stepNumber = stepNumber;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
    }
}
