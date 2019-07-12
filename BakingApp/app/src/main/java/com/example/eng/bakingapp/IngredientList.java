package com.example.eng.bakingapp;

import java.util.ArrayList;

public class IngredientList {
    public ArrayList<Ingredient> ingredients;

    public IngredientList(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
