package com.example.eng.bakingapp;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class StepConverter {
    @TypeConverter
    public String fromRecipeStepList(StepList steplist) {
        if (steplist == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<StepList>() {}.getType();
        String json = gson.toJson(steplist, type);
        return json;
    }

    @TypeConverter
    public StepList toRecipeStepList(String recipeStepString) {
        if (recipeStepString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<StepList>() {}.getType();
        StepList recipeStepList = gson.fromJson(recipeStepString, type);
        return recipeStepList;
    }
}
