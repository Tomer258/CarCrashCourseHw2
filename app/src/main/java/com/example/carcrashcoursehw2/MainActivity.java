package com.example.carcrashcoursehw2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startFastGameBTN,startSlowGameBTN,startGameSensor;




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
            intent.putExtra("speed",500);
            startActivity(intent);
            finish();
        });
    }

}