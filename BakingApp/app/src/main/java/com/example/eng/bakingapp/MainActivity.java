package com.example.eng.bakingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String baseURL = "https://d17h27t6h515a5.cloudfront.net";
    ArrayList<String> names;
    RecipeListFragment recipeFragment;
    HashMap<String, ArrayList<Ingredient>> ingredientsHashMap;
    HashMap<String, ArrayList<Step>> map = new HashMap();
    Bundle bundle = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recipeFragment = (RecipeListFragment) getSupportFragmentManager().findFragmentById(R.id.recipe_list_frag);
        final Retrofit.Builder builder = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RecipeClient client = retrofit.create(RecipeClient.class);
        Call<List<Recipe>> call = client.getRecipes();
        Log.v("beforeenqueue", "response body is  null ");
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, final Response<List<Recipe>> response) {
                Log.v("insid-on-response", "response body is not null ");
                if (response.body().size() != 0) {
                    names = new ArrayList<>();
                    //steps = new ArrayList<>();
                    ingredientsHashMap = new HashMap<>();
                    for (int i = 0; i < response.body().size(); i++) {
                        map.put(response.body().get(i).getRecName(), response.body().get(i).getRecSteps());
                        names.add(response.body().get(i).getRecName());
                        ingredientsHashMap.put(response.body().get(i).getRecName(), response.body().get(i).getRecIngredients());
                        Log.v("namesList", "size()" + names.size());
                        Log.v("nameList", "size()" + ingredientsHashMap.size());
                    }
                    Log.v("nameslist", "size" + names.size());
                    bundle.putSerializable("names", names);
                    bundle.putSerializable("steps", map);
                    bundle.putSerializable("ingredients", ingredientsHashMap);
                    if (recipeFragment != null && recipeFragment.isInLayout()) {
                        Log.v("recipe fragment ", "not null");
                        recipeFragment.getData(bundle);

                        Log.v("name", " " + names.size());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.v("insid-on-failure", "response body is  null ");
                Log.v("Failed-to-load-recipes", "response body is null ", t);
            }
        });

        if (savedInstanceState != null) {
            bundle.putSerializable("names", names);
            bundle.putSerializable("steps", map);
            bundle.putSerializable("ingredients", ingredientsHashMap);
        }
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putStringArrayList("recipeName", names);
        outState.putSerializable("steps", map);
        outState.putSerializable("ingredients", ingredientsHashMap);

    }
}
