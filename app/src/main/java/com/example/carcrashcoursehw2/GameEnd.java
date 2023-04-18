package com.example.carcrashcoursehw2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carcrashcoursehw2.Fragments.MapFragment;
import com.example.carcrashcoursehw2.Fragments.TableFragment;

public class GameEnd extends AppCompatActivity {
    private TableFragment tableFragment;
    private MapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        initFragments();
        beginTransactions();
    }

    private void initFragments() {
        tableFragment = new TableFragment();
        mapFragment = new MapFragment();
    }

    private void beginTransactions() {
        getSupportFragmentManager().beginTransaction().add(R.id.gameEnd_FRAME_table, tableFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.gameEnd_FRAME_map, mapFragment).commit();
    }

}

