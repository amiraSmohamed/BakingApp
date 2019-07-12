package com.example.eng.bakingapp;

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

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    Context c;
    ArrayList<Step> stepsList;
    Intent intent;
    public StepsAdapter(Context context,ArrayList<Step> steps) {
        this.c = context;
        this.stepsList = steps;
    }



    @NonNull
    @Override
    public StepsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.description_list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.descriptionListItem.setText("" + stepsList.get(i).getShortDescription());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(c, RecipeName.class);
                        intent.putExtra("steps", stepsList.get(i));
                        Log.v("recipenameintent", "step first item "+stepsList.get(i).getVideoURL());
                        c.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return stepsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionListItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionListItem = itemView.findViewById(R.id.step_list_item);
            }
        }
}
