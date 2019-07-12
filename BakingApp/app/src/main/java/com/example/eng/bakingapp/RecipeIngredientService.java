package com.example.eng.bakingapp;


import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class RecipeIngredientService extends IntentService{
    ArrayList<String> names =new ArrayList<>();
    HashMap<String,ArrayList<Ingredient>> ingredients = new HashMap<>();
    public static final String ACTION_INGREDIENTS_DISPLAY =
            "com.example.eng.bakingapp.action.ingredients.display";

    public RecipeIngredientService() {
        super(" ");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */

    public RecipeIngredientService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent( Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            names = intent.getStringArrayListExtra("recipeNames");
            ingredients = (HashMap<String, ArrayList<Ingredient>>) intent.getSerializableExtra("ingredients");
//            Log.v("namesList","size()"+names.size());
//            Log.v("nameList","size()"+ingredients.size());
            if (ACTION_INGREDIENTS_DISPLAY.equals(action)) {
                handleActionIngredientsDisplay(ingredients);
            }
        }
    }
    private void handleActionIngredientsDisplay(HashMap<String,ArrayList<Ingredient>> ingredlist) {
        String recipeName = names.get(1);
        String ingredientTextView = null;
        ArrayList<Ingredient> ingredients = ingredlist.get(names.get(1));
        for (int i = 0 ; i<ingredients.size();i++){
            ingredientTextView =(ingredients.get(i)).getIngredientName();
        }
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
//        int [] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this,RecipeWidgetProvider.class));
//        RecipeWidgetProvider.UpdateIngredients(this,appWidgetManager,recipeName,ingredientTextView,appWidgetIds);
    }
    public static void startActionIngredient(Context context) {
        Intent intent = new Intent(context, RecipeIngredientService.class);
        intent.setAction(ACTION_INGREDIENTS_DISPLAY);
        context.startService(intent);
    }

//    @Override
//    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
//        names = intent.getStringArrayListExtra("recipeNames");
//        HashMap<String,ArrayList<Ingredient>> ingredients = (HashMap<String, ArrayList<Ingredient>>) intent.getSerializableExtra("ingredients");
//
//        return super.onStartCommand(intent, flags, startId);
//    }
}
