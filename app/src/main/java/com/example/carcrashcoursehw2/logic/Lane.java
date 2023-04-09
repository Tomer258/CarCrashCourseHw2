package com.example.carcrashcoursehw2.logic;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.carcrashcoursehw2.R;

public class Lane {
    private int isCarInLane = 0;
    private ImageView[] objects;
    private int laneIndex=0;
    private  int isDeerRunning=0;

    public Lane(int isCarInLane, ImageView[] objects) {
        if (objects.length != 8)
            Log.e("Lane Copy", "arrays not same size");
        else {
            this.objects=objects;
        }
        for (int i = 0; i < this.objects.length - 1; i++) {
            this.objects[i].setVisibility(View.INVISIBLE);
        }
        if (isCarInLane == 1) {
            this.setCarInLane(1);
            this.objects[objects.length - 1].setImageResource(R.drawable.car);
        } else {
            this.setCarInLane(0);
            this.objects[objects.length - 1].setImageResource(R.drawable.plyo_w39i_210318_ss4mp_generated);
            this.objects[objects.length - 1].setVisibility(View.INVISIBLE);
        }
    }

    public void setCarInLane(int carToggle) {
        if (carToggle == 1) {
            Log.i("SetCarInLane", "Car toggled");
            this.setIsCarInLane(1);
            this.objects[7].setImageResource(R.drawable.car);
            this.objects[7].setVisibility(View.VISIBLE);
        } else {
            Log.i("SetCarInLane", "Car not toggled");
            this.setIsCarInLane(0);
            this.objects[7].setImageResource(R.drawable.plyo_w39i_210318_ss4mp_generated);
            this.objects[7].setVisibility(View.INVISIBLE);
        }
    }
    public ImageView getObjFromLane(int index)
    {
        return objects[index];
    }
    public int getIsDeerRunning() {
        return isDeerRunning;
    }

    public void setIsDeerRunning(int isDeerRunning) {
        this.isDeerRunning = isDeerRunning;
    }

    public void setLaneIndex(int laneIndex) {
        this.laneIndex = laneIndex;
    }

    public int getLaneIndex() {
        return laneIndex;
    }

    public int getIsCarInLane() {
        return this.isCarInLane;
    }
    public void setIsCarInLane(int isCarInLane) {
        this.isCarInLane = isCarInLane;
    }//setting if car is in lane within the instance



}