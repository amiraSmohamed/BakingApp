package com.example.eng.bakingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class RecipeName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_name);
        RecipeDetailesFragment recipeDetailesFragment = new RecipeDetailesFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.detail_container, recipeDetailesFragment).commit();
        Intent intent = getIntent();
        Step steps = (Step) intent.getSerializableExtra("steps");
        Log.v("urlstep",steps.getVideoURL());
        Log.v("stepsize","@@@@@@"+steps.getShortDescription());
        Bundle bundle = new Bundle();
        bundle.putSerializable("steps",steps);
        //bundle.putInt("position",position);
        recipeDetailesFragment.setArguments(bundle);
    }
}
