package com.example.eng.bakingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;


public class RecipeListFragment extends Fragment {
    ArrayList<String> nameslist;
    View rootView;
    RecyclerView recipeRecyclerView;
    HashMap<String,ArrayList<Step>> steps = null;
    HashMap<String,ArrayList<Ingredient>> ingredient = null;
    public RecipeListFragment() {
        nameslist = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.recipes_list_fragment, container, false);
        Log.v("insidonCreateView", " msggggggggg");
        recipeRecyclerView = rootView.findViewById(R.id.recipes_recycler_view);
        return rootView;
    }

    public ArrayList<String> getData(Bundle arguments) {
        ArrayList<String> names = null;

        if (arguments != null) {
             names = (ArrayList<String>) arguments.getSerializable("names");
             steps = (HashMap<String, ArrayList<Step>>) arguments.getSerializable("steps");
             ingredient = (HashMap<String, ArrayList<Ingredient>>) arguments.get("ingredients");
             initRecycleView(names,steps,ingredient);
             Log.v("names", " is not null" + names.size());
             Log.v("steps", " is not null" + steps.size());
        } else {
            Log.v("names is ", "null");
        }
        return names;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }

    public void initRecycleView(ArrayList<String> names,HashMap<String,ArrayList<Step>> steps,HashMap<String,ArrayList<Ingredient>> ingredientlist) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.recipes_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        RecipesAdapter recipesAdapter = new RecipesAdapter(getContext(), names,steps,ingredientlist);
        Log.v("SizeOfStepsList","is"+steps.size());
        recyclerView.setAdapter(recipesAdapter);
    }

}





