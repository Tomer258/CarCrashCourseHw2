package com.example.carcrashcoursehw2.logic;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.Random;


public class gameManager {

   private final Lane[] lanes;
   private final ImageView[] hearts;
   private int droppedHeartCounter = 0,max=0;
   private final Context c;
   Vibrator vibrator;

   Handler handler1 = new Handler();

   Runnable runnable2;

   public gameManager(Context c, Lane[] lanes, ImageView[] hearts) {
      this.c = c;
      vibrator = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
      this.lanes = lanes;
      this.hearts = hearts;
   }

   public void moveCar(int direction)//1 = Right, 0 = Left, Works only with 5 Lanes!!!!!
   {
      switch (direction) {
         case 1: {
            Log.i("case1", "Going Right");
            assert lanes != null;
            if (lanes[1].getIsCarInLane() == 1) {
               lanes[1].setCarInLane(0);
               lanes[1].getObjFromLane(7).setTag("deer");
               lanes[2].setCarInLane(1);
               lanes[2].getObjFromLane(7).setTag("car");
               if (lanes[2].getLaneIndex()==7)
                  removeHeart();

            } else if (lanes[0].getIsCarInLane() == 1) {
               lanes[0].setCarInLane(0);
               lanes[0].getObjFromLane(7).setTag("deer");
               lanes[1].setCarInLane(1);
               lanes[1].getObjFromLane(7).setTag("car");
               if (lanes[1].getLaneIndex()==7)
                  removeHeart();
            }else if (lanes[2].getIsCarInLane() == 1) {
               lanes[2].setCarInLane(0);
               lanes[2].getObjFromLane(7).setTag("deer");
               lanes[3].setCarInLane(1);
               lanes[3].getObjFromLane(7).setTag("car");
               if (lanes[3].getLaneIndex()==7)
                  removeHeart();
            }else if (lanes[3].getIsCarInLane() == 1) {
               lanes[3].setCarInLane(0);
               lanes[3].getObjFromLane(7).setTag("deer");
               lanes[4].setCarInLane(1);
               lanes[4].getObjFromLane(7).setTag("car");
               if (lanes[4].getLaneIndex()==7)
                  removeHeart();
            }
            break;
         }
         case 0: {
            Log.i("case0", "Going Left");
            assert lanes != null;
            if (lanes[1].getIsCarInLane() == 1) {
               lanes[1].setCarInLane(0);
               lanes[1].getObjFromLane(7).setTag("deer");
               lanes[0].setCarInLane(1);
               lanes[0].getObjFromLane(7).setTag("car");
               if (lanes[0].getLaneIndex()==7)
                  removeHeart();
            } else if (lanes[2].getIsCarInLane() == 1) {
               lanes[2].setCarInLane(0);
               lanes[2].getObjFromLane(7).setTag("deer");
               lanes[1].setCarInLane(1);
               lanes[1].getObjFromLane(7).setTag("car");
               if (lanes[1].getLaneIndex()==7)
                  removeHeart();
            }else if (lanes[3].getIsCarInLane() == 1) {
               lanes[3].setCarInLane(0);
               lanes[3].getObjFromLane(7).setTag("deer");
               lanes[2].setCarInLane(1);
               lanes[2].getObjFromLane(7).setTag("car");
               if (lanes[2].getLaneIndex()==7)
                  removeHeart();
            }else if (lanes[4].getIsCarInLane() == 1) {
               lanes[4].setCarInLane(0);
               lanes[4].getObjFromLane(7).setTag("deer");
               lanes[3].setCarInLane(1);
               lanes[3].getObjFromLane(7).setTag("car");
               if (lanes[3].getLaneIndex()==7)
                  removeHeart();
            }
            break;
         }
      }
   }

   public void removeHeart()
   {
      Log.i("Game Manager: ", "CRASH!!!!!!!!!!!!!!!! -1 HP");
      if (this.hearts.length - 1 - droppedHeartCounter >= 0) {
         this.hearts[droppedHeartCounter].setVisibility(View.INVISIBLE);
         droppedHeartCounter++;
      } else {
         droppedHeartCounter = 0;
         gameOver();
      }
      new Handler(Looper.getMainLooper()).post(() -> {
         Toast.makeText(c, "Ouch", Toast.LENGTH_SHORT).show();
         // Vibrate for 500 milliseconds
         vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
      });
   }

   public void runGame() {

      runnable2 = new Runnable() {
         @Override
         public void run() {

            for (Lane lane : lanes)
            {
               if (lane.getLaneIndex()==7 && lane.getIsCarInLane()==0)
               {
                  lane.getObjFromLane(lane.getLaneIndex()).setVisibility(View.INVISIBLE);
                  lane.setIsDeerRunning(0);
                  lane.setLaneIndex(0);
                  max--;
               }
               if(lane.getLaneIndex()==6 && lane.getIsCarInLane()==1)
               {
                  removeHeart();
               }

               if (lane.getLaneIndex()!=7 && lane.getObjFromLane(lane.getLaneIndex()).getVisibility()==View.VISIBLE)
               {
                  Log.i("RunGame2 Before",lane.getLaneIndex()+"");
                  lane.getObjFromLane(lane.getLaneIndex()).setVisibility(View.INVISIBLE);
                  lane.setLaneIndex(lane.getLaneIndex()+1);
                  lane.getObjFromLane(lane.getLaneIndex()).setVisibility(View.VISIBLE);
               }
            }

            int whichLane=generateLane();
            int free=1;
            if (lanes[whichLane].getIsDeerRunning()==0)
            {
               if (max==4)
               {
                  for (int i = 0; i < lanes.length; i++) {
                     if (i!=whichLane) {
                        if (lanes[i].getLaneIndex()<2)
                           free=0;
                     }
                  }
                  if (free==1)
                  {
                     lanes[whichLane].getObjFromLane(0).setVisibility(View.VISIBLE);
                     lanes[whichLane].setIsDeerRunning(1);
                     max++;
                  }
               }
               lanes[whichLane].getObjFromLane(0).setVisibility(View.VISIBLE);
               lanes[whichLane].setIsDeerRunning(1);
               max++;
            }
            handler1.postDelayed(this, 500);
         }
      };
      handler1.postDelayed(runnable2,0);
   }
   private int generateLane()
   {
      Random r = new Random();
      return r.nextInt(5);
   }
   private void gameOver() {
      Log.i("Game Manager: ", "GAME OVER");
   }
   public void killHandler()
   {
      handler1.removeCallbacks(null);
   }
   public void restartHandler()
   {
      runGame();
   }
}