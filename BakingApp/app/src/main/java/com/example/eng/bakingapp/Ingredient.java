package com.example.eng.bakingapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ingredient implements Serializable  {

    @SerializedName("quantity")
    @Expose
    private double quantity;

    @SerializedName("measure")
    @Expose
    private String measure;

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @SerializedName("ingredient")
    @Expose
    private String ingredientName;
}
