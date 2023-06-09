package com.example.carcrashcoursehw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.carcrashcoursehw2.Utilities.DeviceLocationManager;
import com.example.carcrashcoursehw2.Utilities.SignalGenerator;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startFastGameBTN,startSlowGameBTN,startGameSensor,leaderBoard;




        startFastGameBTN=findViewById(R.id.startGameWithButtonsFast);
        startFastGameBTN.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,game_content.class);
            intent.putExtra("speed",250);
            intent.putExtra("mode",0);
            startActivity(intent);
            finish();
        });

        startSlowGameBTN=findViewById(R.id.startGameWithButtonsSlow);
        startSlowGameBTN.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,game_content.class);
            intent.putExtra("speed",500);
            intent.putExtra("mode",0);
            startActivity(intent);
            finish();
        });

        startGameSensor=findViewById(R.id.main_BTN_startGameWithSensor);
        startGameSensor.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,game_content.class);
            intent.putExtra("mode",1);
            intent.putExtra("speed",400);
            startActivity(intent);
            finish();
        });

        leaderBoard=findViewById(R.id.main_BTN_leadBoard);
        leaderBoard.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,GameEnd.class);
            startActivity(intent);
            finish();
        });

        if (DeviceLocationManager.getInstance().isGPSOn()) //check is GPS Enabled
            DeviceLocationManager.getInstance().checkLocationPermission(this);
        else
            SignalGenerator.getInstance().toast("GPS is off", Toast.LENGTH_SHORT);

    }

}