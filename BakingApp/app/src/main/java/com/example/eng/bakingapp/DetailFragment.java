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

public class DetailFragment extends Fragment {
    View rootView;
    RecyclerView stepsRecyclerView;
    ArrayList<Step> stepsList;
    Context activity = null;

    public DetailFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(activity);
        activity = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            ArrayList<Step> steps = (ArrayList<Step>) savedInstanceState.getSerializable("list");
            initRecycleView(steps);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.detailfragment, container, false);
        stepsRecyclerView = rootView.findViewById(R.id.description_recycler_view);
        stepsList = setData(((DetailActivity) getActivity()).getData());
        Log.v("stepDetailFragment", " " + stepsList.size());
        return rootView;
    }

    public ArrayList<Step> setData(Bundle arguments) {
        ArrayList<Step> steps = null;
        Log.v("insideSetData", "before getArgument ");
        if (arguments != null) {
            steps = (ArrayList<Step>) arguments.getSerializable("descripSteps");
            Log.v("steps", "is not null in detail" + steps.get(0));
            initRecycleView(steps);
        } else {
            Log.v("stepsis ", "null");
        }
        return steps;
    }

    public void initRecycleView(ArrayList<Step> steps) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = rootView.findViewById(R.id.description_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        StepsAdapter stepsAdapter = new StepsAdapter(getContext(), steps);
        recyclerView.setAdapter(stepsAdapter);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("list", stepsList);
    }


}
