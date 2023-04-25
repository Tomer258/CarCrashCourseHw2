package com.example.carcrashcoursehw2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.carcrashcoursehw2.Fragments.MapFragment;
import com.example.carcrashcoursehw2.Fragments.TableFragment;
import com.example.carcrashcoursehw2.Interfaces.CallBack_sendXY;
import com.example.carcrashcoursehw2.Utilities.DeviceLocationManager;

public class GameEnd extends AppCompatActivity {
    private TableFragment tableFragment;
    private MapFragment mapFragment;

    CallBack_sendXY callBack_sendXY=new CallBack_sendXY() {
        @Override
        public void sendXy(double x, double y) {
            mapFragment.showUserLocation(x,y);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        initFragments();
        tableFragment.setCallBack_sendXY(callBack_sendXY);
        beginTransactions();
    }

    @Override
    protected void onPause() {
        super.onPause();
        DeviceLocationManager.getInstance().stopLocationUpdates();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DeviceLocationManager.getInstance().startLocationUpdates(this);
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

