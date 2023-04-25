package com.example.carcrashcoursehw2.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScoreModel {
    int score;
    int distance;

    double x,y;
    String date;
    public ScoreModel(int score, int distance,double x,double y) {
        this.score = score;
        this.distance = distance;
        this.x=x;
        this.y=y;
        this.date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }

    public int getScore() {
        return this.score;
    }

    public String getDate() {
        return this.date;
    }

    public int getDistance() {
        return this.distance;
    }


}
