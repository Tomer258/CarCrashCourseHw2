package com.example.carcrashcoursehw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String DB_FILE = "DB_FILE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startFastGameBTN,startSlowGameBTN,startGameSensor;




        startFastGameBTN=findViewById(R.id.startGameWithButtonsFast);
        startFastGameBTN.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = this.getSharedPreferences(DB_FILE, this.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("speed", "fast");
            editor.apply();
            startActivity(new Intent(MainActivity.this,game_content.class));
            finish();
        });

        startSlowGameBTN=findViewById(R.id.startGameWithButtonsSlow);
        startSlowGameBTN.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = this.getSharedPreferences(DB_FILE, this.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("speed", "slow");
            editor.apply();
            startActivity(new Intent(MainActivity.this,game_content.class));
            finish();
        });

        startGameSensor=findViewById(R.id.main_BTN_startGameWithSensor);
        startGameSensor.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,game_content_sensor.class));
            finish();
        });
    }

}