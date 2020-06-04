package com.derandecker.bakingapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Steps {
    @PrimaryKey
    public int stepId;
    public int recipeId;
    public String shortDescription;
    public String description;
    public String videoURL;

    @Ignore
    public Steps(){
    }

    public Steps(int stepId, int recipeId, String shortDescription,
                 String description, String videoURL){
        this.stepId = stepId;
        this.recipeId = recipeId;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
    }

}
