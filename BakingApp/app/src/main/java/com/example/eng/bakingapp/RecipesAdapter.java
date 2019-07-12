package com.example.eng.bakingapp;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

   static ArrayList<String> recipesNamesList;
    Context c;
    HashMap<String,ArrayList<Step>> steps;
    HashMap<String,ArrayList<Ingredient>> ingredientMap;
    public RecipesAdapter(Context context, ArrayList<String> recipesNamesList, HashMap<String, ArrayList<Step>> steps, HashMap<String,ArrayList<Ingredient>> ingredientlist) {
        this.recipesNamesList = recipesNamesList;
        this.c = context;
        this.steps = steps;
        this.ingredientMap = ingredientlist;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recpie_list_item, viewGroup, false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.recipeNameTV.setText("" + recipesNamesList.get(i));
    }


    @Override
    public int getItemCount() {
        return recipesNamesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView recipeNameTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeNameTV = itemView.findViewById(R.id.recipe_name_tv);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent widget = new Intent();
                        widget.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                        widget.putExtra("recipeName",recipesNamesList.get(getAdapterPosition()));
                        Log.v("recipeNametext",recipesNamesList.get(getAdapterPosition()));
                        String ingredientTV = "";
//                        for(int i =0 ; i<ingredientMap.size();i++){
//                            ingredientTV += ingredientMap.get(recipesNamesList.get(getAdapterPosition())).getIngredientName()+"\n";
//                        }
                        for(Ingredient ingredient   : ingredientMap.get(recipesNamesList.get(getAdapterPosition()))){
                            ingredientTV += ingredient.getIngredientName()+"\n";
                        }
                        Log.v("ingredientTV",ingredientTV);
                        widget.putExtra("ingredient",ingredientTV);
                        c.sendBroadcast(widget);

                        Intent intent = new Intent(c, DetailActivity.class);
                        intent.putExtra("steps", steps.get(recipesNamesList.get(getAdapterPosition())));
                        Log.v("recipenameintent", "step first item "+steps.get(recipesNamesList.get(getAdapterPosition())));
                        c.startActivity(intent);
                    }
                });
        }
    }
}
