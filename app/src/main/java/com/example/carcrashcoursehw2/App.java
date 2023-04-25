package com.example.carcrashcoursehw2;

import android.app.Application;

import com.example.carcrashcoursehw2.Utilities.sharedPref;
import com.example.carcrashcoursehw2.Utilities.SignalGenerator;
import com.paz.prefy_lib.Prefy;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPref.init(this);
        SignalGenerator.init(this);
        Prefy.init(this,false);
    }
}