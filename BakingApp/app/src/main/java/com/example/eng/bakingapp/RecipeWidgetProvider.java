package com.example.eng.bakingapp;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidgetProvider extends AppWidgetProvider {
    String recipeName = "", ingredients = "";
    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int  appWidgetId) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget_provider);
        views.setTextViewText(R.id.appwidget_text, recipeName);
        Log.e("RecipeWidget", "name   "+recipeName);
        views.setTextViewText(R.id.ingredient,ingredients);
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.e("RecipeWidget", "on update   "+recipeName);
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager,appWidgetId);
        }
    }



    public void UpdateIngredients(Context context , AppWidgetManager appWidgetManager, int [] appWidgetIds){

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("RecipeWidget", "recieve  "+recipeName);
        if(intent.getAction().equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE))
            Log.e("RecipeWidget", "recieve if  "+AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        {
          recipeName = intent.getStringExtra("recipeName");
            Log.e("RecipeNameWidget", "recieve if  "+recipeName);
            ingredients = intent.getStringExtra("ingredient");

            if(intent!=null) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
                ComponentName thisAppWidget = new ComponentName(context.getPackageName(), RecipeWidgetProvider.class.getName());
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);

                onUpdate(context, appWidgetManager, appWidgetIds);
            }

        }

        super.onReceive(context, intent);

    }
}

