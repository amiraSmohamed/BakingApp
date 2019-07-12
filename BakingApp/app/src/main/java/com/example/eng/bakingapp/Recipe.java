package com.example.eng.bakingapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;


public class Recipe implements Serializable {


    public Recipe() {
    }
    @SerializedName("id")
    @Expose
   private int RecId ;

    @SerializedName("name")
    @Expose
    private String RecName;

    @SerializedName("ingredients")
    @Expose
    private ArrayList<Ingredient> RecIngredients;

    @SerializedName("steps")
    @Expose
    private ArrayList<Step> RecSteps ;

    @SerializedName("servings")
    @Expose
    private int RecServings ;

    public int getRecId() {
        return RecId;
    }

    public void setRecId(int recId) {
        RecId = recId;
    }

    public String getRecName() {
        return RecName;
    }

    public void setRecName(String recName) {
        RecName = recName;
    }

    public ArrayList<Ingredient> getRecIngredients() {

        return RecIngredients;
    }

    public void setRecIngredients(ArrayList<Ingredient> recIngredients) {

        RecIngredients = recIngredients;
    }

    public ArrayList<Step> getRecSteps() {

        return RecSteps;
    }

    public void setRecSteps(ArrayList<Step> recSteps) {
        RecSteps = recSteps;
    }

    public int getRecServings() {
        return RecServings;
    }

    public void setRecServings(int recServings) {
        RecServings = recServings;
    }

    public String getRecImageURl() {
        return RecImageURl;
    }

    public void setRecImageURl(String recImageURl) {
        RecImageURl = recImageURl;
    }

    @SerializedName("image")
    @Expose
    private String RecImageURl;

}
