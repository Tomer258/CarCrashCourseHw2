package com.example.carcrashcoursehw2;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.carcrashcoursehw2.Interfaces.StepCallback;
import com.example.carcrashcoursehw2.Utilities.DeviceLocationManager;
import com.example.carcrashcoursehw2.Utilities.SignalGenerator;
import com.example.carcrashcoursehw2.Utilities.StepDetector;
import com.example.carcrashcoursehw2.logic.gameManager;
import com.example.carcrashcoursehw2.logic.Lane;

public class game_content extends AppCompatActivity implements SensorEventListener {
    private ImageButton rightBtn,leftBtn;
    private TextView score,points;

    private SensorManager sensorManager;
    private Sensor gyroscope;
    private float rollAngle = 0.0f;
    private int workMod=2;
    private StepDetector stepDetector;



    private gameManager gm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_content);
        DeviceLocationManager.getInstance().startLocationUpdates(this);
        initialStartingValues();
        initialGameManager();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (workMod==1)
        {
            //sensorManager.unregisterListener(this);
            stepDetector.stop();
        }

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
        if (workMod==1)
        {
           // sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
            stepDetector.start();
        }

        gm.restartHandler();
    }

    private void initialGameManager() {

        int delay=getIntent().getIntExtra("speed",1000);

        score=findViewById(R.id.score);
        points=findViewById(R.id.points);
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
            new ImageView[]{findViewById(R.id.heart1),findViewById(R.id.heart2),findViewById(R.id.heart3)},score,delay,points);

    }
    private void initialStartingValues() {
        int mode=getIntent().getIntExtra("mode",2);
        this.workMod=mode;
        switch (mode)
        {
            case 0:
            {
                setBtnOnClicks();
                break;
            }
            case 1:
            {
                LinearLayout linearLayout=(LinearLayout)findViewById(R.id.buttonsLayout);
                linearLayout.setVisibility(View.GONE);
                initialSensor();
                break;
            }
        }

    }

    private void initialSensor()
    {
        //sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        //gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
       // if (gyroscope==null)
        //{
            //SignalGenerator.getInstance().toast("Gyroscope sensor is not available on the device",0 );
           // finish();
       // }
        stepDetector = new StepDetector(this, new StepCallback() {
            @Override
            public void xUp() {
                gm.moveCar(1);
            }

            @Override
            public void xDown() {
                gm.moveCar(0);
            }

            @Override
            public void yUp() {
                gm.changeSpeed(1);
            }

            @Override
            public void yDown() {
                gm.changeSpeed(0);
            }
        });
    }

    private void setBtnOnClicks() {
        rightBtn=findViewById(R.id.RightBTN);
        leftBtn=findViewById(R.id.leftBTN);

        rightBtn.setOnClickListener(v -> gm.moveCar(1));
        leftBtn.setOnClickListener(v -> gm.moveCar(0));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        /*if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            float roll = z * (180 / (float) Math.PI) * (event.timestamp - rollAngle) / 1000000000.0f;
            rollAngle = event.timestamp;
            if (roll < -2.0f)
            {
                Log.d("Sensor movement!!!!","MOVING RIGHT");
                gm.moveCar(1);
            }
            else if (roll > 2.0f)
            {
                gm.moveCar(0);
                Log.d("Sensor movement!!!!","MOVING LEFT");
            }
            else {
                // Phone is not rolled
            }

        }*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
    }


}