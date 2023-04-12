package com.example.carcrashcoursehw2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carcrashcoursehw2.logic.gameManager;
import com.example.carcrashcoursehw2.logic.Lane;

public class game_content extends AppCompatActivity {
    private ImageButton rightBtn,leftBtn;
    private TextView score;
    private gameManager gm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_content);
        initialStartingValues();
        initialGameManager();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gm.killHandler();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        gm.killHandler();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gm.restartHandler();
    }

    private void initialGameManager() {

        int delay=getIntent().getIntExtra("speed",1000);

        score=findViewById(R.id.score);
        ImageView[] iLane1 ={findViewById(R.id.firstLaneDeer1),findViewById(R.id.firstLaneDeer2),
                            findViewById(R.id.firstLaneDeer3),findViewById(R.id.firstLaneDeer4),
                            findViewById(R.id.firstLaneDeer5),findViewById(R.id.firstLaneDeer6),
                            findViewById(R.id.firstLaneDeer7),findViewById(R.id.specialPosFirstLane)};

        ImageView[] iLane2 ={findViewById(R.id.SecondLaneDeer1),findViewById(R.id.SecondLaneDeer2),
                findViewById(R.id.SecondLaneDeer3),findViewById(R.id.SecondLaneDeer4),
                findViewById(R.id.SecondLaneDeer5),findViewById(R.id.SecondLaneDeer6),
                findViewById(R.id.SecondLaneDeer7),findViewById(R.id.specialPosSecondLane)};

        ImageView[] iLane3 ={findViewById(R.id.ThirdLaneDeer1),findViewById(R.id.ThirdLaneDeer2),
                findViewById(R.id.ThirdLaneDeer3),findViewById(R.id.ThirdLaneDeer4),
                findViewById(R.id.ThirdLaneDeer5),findViewById(R.id.ThirdLaneDeer6),
                findViewById(R.id.ThirdLaneDeer7),findViewById(R.id.specialPosThirdLane)};

        ImageView[] iLane4={findViewById(R.id.ForthLaneDeer1),findViewById(R.id.ForthLaneDeer2),
                findViewById(R.id.ForthLaneDeer3),findViewById(R.id.ForthLaneDeer4),
                findViewById(R.id.ForthLaneDeer5),findViewById(R.id.ForthLaneDeer6),
                findViewById(R.id.ForthLaneDeer7),findViewById(R.id.specialPosForthLane)};

        ImageView[] iLane5={findViewById(R.id.FifthLaneDeer1),findViewById(R.id.FifthLaneDeer2),
                findViewById(R.id.FifthLaneDeer3),findViewById(R.id.FifthLaneDeer4),
                findViewById(R.id.FifthLaneDeer5),findViewById(R.id.FifthLaneDeer6),
                findViewById(R.id.FifthLaneDeer7),findViewById(R.id.specialPosFifthLane)};

        Lane mLane1 = new Lane(0, iLane1);
        Lane mLane2= new Lane(0,iLane2);
        Lane mLane3= new Lane(1,iLane3);
        Lane mLane4= new Lane(0,iLane4);
        Lane mLane5= new Lane(0,iLane5);
        gm =new gameManager(this,new Lane[]{mLane1, mLane2, mLane3, mLane4, mLane5},
            new ImageView[]{findViewById(R.id.heart1),findViewById(R.id.heart2),findViewById(R.id.heart3)},score,delay);

    }
    private void initialStartingValues() {
        rightBtn=findViewById(R.id.RightBTN);
        leftBtn=findViewById(R.id.leftBTN);
        setBtnOnClicks();
    }

    private void setBtnOnClicks() {
        rightBtn.setOnClickListener(v -> gm.moveCar(1));
        leftBtn.setOnClickListener(v -> gm.moveCar(0));
    }




}