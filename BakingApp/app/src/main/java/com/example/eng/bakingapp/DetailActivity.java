package com.example.eng.bakingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    Bundle bundle;
    ArrayList<Step> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        DetailFragment detailFragment = new DetailFragment();
        RecipeDetailesFragment recipeDetailesFragment = new RecipeDetailesFragment();
        if (isTablet(this)) {
            Log.v("isTablet", "true");
            getSupportFragmentManager().beginTransaction().add(R.id.list_fragment, detailFragment).commit();
            getSupportFragmentManager().beginTransaction().add(R.id.detailes_fragment, recipeDetailesFragment).commit();
        } else {
            Log.v("isTablet", "false");
            getSupportFragmentManager().beginTransaction().add(R.id.container, detailFragment).commit();
        }
        Intent detailIntent = getIntent();
        steps = (ArrayList) detailIntent.getParcelableArrayListExtra("steps");
        if (detailIntent != null) {
            bundle = new Bundle();
            bundle.putSerializable("descripSteps", steps);
        }
        if (savedInstanceState == null) {

            if (isTablet(this)) {
                Log.v("isTablet", "true");
                getSupportFragmentManager().beginTransaction().replace(R.id.list_fragment, detailFragment).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.detailes_fragment, recipeDetailesFragment).commit();
            } else {
                Log.v("isTablet", "false");
                getSupportFragmentManager().beginTransaction().replace(R.id.container, detailFragment).commit();
            }
        }
    }

    public Bundle getData() {

        return bundle;
    }

    public boolean isTablet(Context context) {
        return getResources().getBoolean(R.bool.isTablet);

    }
}
