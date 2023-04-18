package com.example.carcrashcoursehw2.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScoreModel {
    int score;

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    String date;

    public ScoreModel(int score)
    {
        this.score=score;
        this.date=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
    }
}
