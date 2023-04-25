package com.example.carcrashcoursehw2.logic;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.carcrashcoursehw2.GameEnd;
import com.example.carcrashcoursehw2.R;
import com.example.carcrashcoursehw2.RecyclerView.ScoreList;
import com.example.carcrashcoursehw2.RecyclerView.ScoreModel;
import com.example.carcrashcoursehw2.Utilities.DeviceLocationManager;
import com.example.carcrashcoursehw2.Utilities.SignalGenerator;
import com.example.carcrashcoursehw2.Utilities.sharedPref;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paz.prefy_lib.Prefy;

import java.util.ArrayList;
import java.util.Random;


public class gameManager {
   Prefy prefy= Prefy.getInstance();
   ScoreModel scoreModel;
   ScoreList scoreList;
   private  final int POINTS_ADD = 10;
   Random r = new Random();
   private final int SCORE_ADD=8;
   private final TextView score,points;
   private final Lane[] lanes;
   private final ImageView[] hearts;
   private int droppedHeartCounter = 0,max=0,scoreInNum=0,distanceCounter=0,delay,pointsInNum=0;
   private final Context c;
   Vibrator vibrator;
   private MediaPlayer mediaPlayer;

   Handler handler1 = new Handler();

   Runnable runnable2;

   public gameManager(Context c, Lane[] lanes, ImageView[] hearts, TextView score,int delay, TextView points) {
      this.c = c;
      vibrator = (Vibrator) c.getSystemService(Context.VIBRATOR_SERVICE);
      this.lanes = lanes;
      this.hearts = hearts;
      this.score=score;
      this.points=points;
      mediaPlayer=MediaPlayer.create(c, R.raw.crash);
      this.delay=delay;
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
                  if (lanes[2].getIsCoin()==1)
                  {
                     playCoin();
                     addPoints();
                  }
                  else
                  {
                     playCrash();
                     removeHeart();
                  }
            } else if (lanes[0].getIsCarInLane() == 1) {
               lanes[0].setCarInLane(0);
               lanes[0].getObjFromLane(7).setTag("deer");
               lanes[1].setCarInLane(1);
               lanes[1].getObjFromLane(7).setTag("car");
               if (lanes[1].getLaneIndex()==7) {
                  if (lanes[1].getIsCoin() == 1)
                  {
                     playCoin();
                     addPoints();
                  }
                  else
                  {
                     playCrash();
                     removeHeart();
                  }
               }
            }else if (lanes[2].getIsCarInLane() == 1) {
               lanes[2].setCarInLane(0);
               lanes[2].getObjFromLane(7).setTag("deer");
               lanes[3].setCarInLane(1);
               lanes[3].getObjFromLane(7).setTag("car");
               if (lanes[3].getLaneIndex()==7)
                  if (lanes[3].getIsCoin()==1)
                  {
                     playCoin();
                     addPoints();
                  }
                  else
                  {
                     playCrash();
                     removeHeart();
                  }
            }else if (lanes[3].getIsCarInLane() == 1) {
               lanes[3].setCarInLane(0);
               lanes[3].getObjFromLane(7).setTag("deer");
               lanes[4].setCarInLane(1);
               lanes[4].getObjFromLane(7).setTag("car");
               if (lanes[4].getLaneIndex()==7)
                  if (lanes[4].getIsCoin()==1)
                  {
                     playCoin();
                     addPoints();
                  }
                  else
                  {
                     playCrash();
                     removeHeart();
                  }
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
                  if (lanes[0].getIsCoin()==1)
                  {
                     playCoin();
                     addPoints();
                  }
                  else
                  {
                     playCrash();
                     removeHeart();
                  }
            } else if (lanes[2].getIsCarInLane() == 1) {
               lanes[2].setCarInLane(0);
               lanes[2].getObjFromLane(7).setTag("deer");
               lanes[1].setCarInLane(1);
               lanes[1].getObjFromLane(7).setTag("car");
               if (lanes[1].getLaneIndex()==7)
                  if (lanes[1].getIsCoin()==1)
                  {
                     playCoin();
                     addPoints();
                  }
                  else
                  {
                     playCrash();
                     removeHeart();
                  }
            }else if (lanes[3].getIsCarInLane() == 1) {
               lanes[3].setCarInLane(0);
               lanes[3].getObjFromLane(7).setTag("deer");
               lanes[2].setCarInLane(1);
               lanes[2].getObjFromLane(7).setTag("car");
               if (lanes[2].getLaneIndex()==7)
                  if (lanes[2].getIsCoin()==1)
                  {
                     playCoin();
                     addPoints();
                  }
                  else
                  {
                     playCrash();
                     removeHeart();
                  }
            }else if (lanes[4].getIsCarInLane() == 1) {
               lanes[4].setCarInLane(0);
               lanes[4].getObjFromLane(7).setTag("deer");
               lanes[3].setCarInLane(1);
               lanes[3].getObjFromLane(7).setTag("car");
               if (lanes[3].getLaneIndex()==7)
                  if (lanes[3].getIsCoin()==1)
                     {
                       playCoin();
                       addPoints();
                      }
                  else
                  {
                     playCrash();
                     removeHeart();
                  }
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
         //vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
         SignalGenerator.getInstance().vibrate(500);
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
                  if (lane.getIsCoin()==1)
                     resetLaneToDeer(lane);
               }
               if(lane.getLaneIndex()==6 && lane.getIsCarInLane()==1)
               {
                  lane.getObjFromLane(lane.getLaneIndex()).setVisibility(View.INVISIBLE);
                  lane.setIsDeerRunning(0);
                  lane.setLaneIndex(0);
                  max--;
                  if (lane.getIsCoin()==1)
                  {
                     playCoin();
                     addPoints();
                  }
                  else
                  {
                     playCrash();
                     removeHeart();
                  }
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
            boolean chanceToDropCoin=spawnCoinInLaneChance();
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
                     if (chanceToDropCoin)
                        generateCoinLane(lanes[whichLane]);
                     lanes[whichLane].getObjFromLane(0).setVisibility(View.VISIBLE);
                     lanes[whichLane].setIsDeerRunning(1);
                     max++;
                  }
               }
               if (chanceToDropCoin)
                  generateCoinLane(lanes[whichLane]);
               lanes[whichLane].getObjFromLane(0).setVisibility(View.VISIBLE);
               lanes[whichLane].setIsDeerRunning(1);
               max++;
            }
            if (distanceCounter==8)
            {
               distanceCounter = 0;
               addScore();
            }
            else
               distanceCounter++;
            handler1.postDelayed(this, delay);
         }
      };
      handler1.postDelayed(runnable2,0);
   }
   private int generateLane()
   {
      return r.nextInt(5);
   }
   private void gameOver() {
      double x = DeviceLocationManager.getInstance().getX();
      double y = DeviceLocationManager.getInstance().getY();
      String fromJson=sharedPref.getInstance().getString("scores","");
      this.scoreList=new Gson().fromJson(fromJson, ScoreList.class);
      if (scoreList==null)
      {
         scoreList=new ScoreList();
      }
      scoreList.addScore(this.pointsInNum,this.scoreInNum, x, y);
      Log.i("XY","--------------------------");
      Log.i("XY","X: " +DeviceLocationManager.getInstance().getX() +" Y: " +DeviceLocationManager.getInstance().getY());
      Log.i("XY","--------------------------");
      String toJson= new Gson().toJson(scoreList);
      sharedPref.getInstance().putString("scores",toJson);
      c.startActivity(new Intent(c, GameEnd.class));
      Log.i("Game Manager: ", "GAME OVER");
   }
   public void killHandler()
   {
      handler1.removeCallbacks(runnable2);
   }
   public void restartHandler()
   {
      runGame();
   }
   private void generateCoinLane(Lane lane)
   {
      lane.setLaneToCoin();
      lane.setIsCoin(1);
   }
   private void resetLaneToDeer(Lane lane)
   {
      lane.setLaneToDeer();
      lane.setIsCoin(0);
   }
   private boolean spawnCoinInLaneChance()
   {
      return (r.nextInt(100)>=40);
   }
   private void addPoints() {
      pointsInNum+=POINTS_ADD;
      points.setText("" +pointsInNum);
   }
   private  void addScore()
   {
      scoreInNum+=SCORE_ADD;
      score.setText("" +scoreInNum);
   }
   private void playCoin()
   {
      if (mediaPlayer!=null) mediaPlayer.release();
      mediaPlayer = MediaPlayer.create(c, R.raw.coin);
      mediaPlayer.start();
   }
   private void playCrash()
   {
      if (mediaPlayer!=null) mediaPlayer.release();
      mediaPlayer = MediaPlayer.create(c, R.raw.crash);
      mediaPlayer.start();
   }
}