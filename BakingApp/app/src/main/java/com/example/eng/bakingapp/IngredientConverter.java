package com.example.eng.bakingapp;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class IngredientConverter {
    @TypeConverter
    public String fromRecipeIngredientList(IngredientList ingredientlist) {
        if (ingredientlist == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<IngredientList>() {}.getType();
        String json = gson.toJson(ingredientlist, type);
        return json;
    }

    @TypeConverter
    public IngredientList toRecipeIngredientsList(String ingredientString) {
        if (ingredientString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<IngredientList>() {}.getType();
        IngredientList recipeingredientList = gson.fromJson(ingredientString, type);
        return recipeingredientList;
    }
}
