package com.example.carcrashcoursehw2.RecyclerView;

import java.util.ArrayList;
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
        this.scores.add(new ScoreModel(points,dist,x,y));
        scores.sort(new scoreComparator());
        Collections.reverse(scores);
        if (scores.size()>10)
            scores.remove(10);
    }
    static class scoreComparator implements Comparator<ScoreModel>
    {
        @Override
        public int compare(ScoreModel scoreModel, ScoreModel t1) {
            int outcome=scoreModel.getScore() - t1.getScore();
            if (outcome!=0)
            {
                return outcome;
            }
            else
            {
                return scoreModel.getDistance()- t1.getDistance();
            }
        }
    }

}
