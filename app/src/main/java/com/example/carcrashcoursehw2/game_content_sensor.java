package com.example.carcrashcoursehw2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.carcrashcoursehw2.logic.Lane;
import com.example.carcrashcoursehw2.logic.gameManager;

public class game_content_sensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gyroscope;
    private float rollAngle = 0.0f;

    private gameManager gm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_content_sensor);
        initialStartingValues();
        initialGameManager();

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
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
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        //gm.restartHandler();
    }

    private void initialGameManager() {
        TextView score = findViewById(R.id.score);
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
       // gm =new gameManager(this,new Lane[]{mLane1, mLane2, mLane3, mLane4, mLane5},
            //new ImageView[]{findViewById(R.id.heart1),findViewById(R.id.heart2),findViewById(R.id.heart3)}, score,500,);

    }
    private void initialStartingValues() {
        sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float deltaRoll = y * (180 / (float) Math.PI) * (event.timestamp - rollAngle) / 1000000000.0f;
            rollAngle = event.timestamp;

            if (deltaRoll < -5) {
                // Phone is rolled left
                gm.moveCar(0);
            } else if (deltaRoll > 5) {
                // Phone is rolled right
                gm.moveCar(1);
            } else {
                // Phone is not rolled
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
    }




}