package com.example.carcrashcoursehw2.RecyclerView;

import java.util.ArrayList;

public class ScoreList
{
    private  ArrayList<ScoreModel> scores=new ArrayList<>();

    public ScoreList() {
    }

    public ArrayList<ScoreModel> getScores() {
        return scores;
    }

    public void setScores(ArrayList<ScoreModel> scores) {
        this.scores = scores;
    }
}
