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
        Button startGameBTN;

        startGameBTN=findViewById(R.id.main_BTN_startGame);
        startGameBTN.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,game_content.class));
            finish();
        });
    }

}