package com.example.carcrashcoursehw2.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ScoreList
{
    private  ArrayList<ScoreModel> scores;

    public ScoreList() {
        scores=new ArrayList<>();
    }

    public ArrayList<ScoreModel> getScores() {
        return this.scores;
    }

    public void addScore(int points,int dist,double x,double y)
    {
        if (scores.size()==0)
            this.scores.add(new ScoreModel(points,dist,x,y));
        for (int i = 0; i < this.scores.size(); i++) {
            if (this.scores.get(i).scoreCompare(points,dist))
            {
                this.scores.add(new ScoreModel(points,dist,x,y));
                break;
            }

        }
        scores.sort((scoreModel, t1) -> t1.score- scoreModel.score);
        if (scores.size()>10)
            scores.remove(10);
    }

}
