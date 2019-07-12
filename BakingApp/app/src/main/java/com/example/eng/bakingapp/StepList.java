package com.example.eng.bakingapp;

import java.util.ArrayList;

public class StepList  {
    public ArrayList<Step> steps;

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public StepList(ArrayList<Step> steps) {
        this.steps = steps;
    }
}
