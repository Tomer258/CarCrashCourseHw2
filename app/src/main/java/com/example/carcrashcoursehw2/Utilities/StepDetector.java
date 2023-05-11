package com.example.carcrashcoursehw2.Utilities;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.example.carcrashcoursehw2.Interfaces.StepCallback;


public class StepDetector {
    private Sensor sensor;

    private SensorManager sensorManager;

    private StepCallback stepCallback;
    private long timestamp = 0;

    private SensorEventListener sensorEventListener;

    public StepDetector(Context context, StepCallback stepCallback) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.stepCallback = stepCallback;
        initEventListener();
    }

    private void initEventListener() {
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float x = event.values[0];
                float y = event.values[1];

                calculateStep(x, y);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    private void calculateStep(float x, float y) {
        if (System.currentTimeMillis() - timestamp > 250) {
            timestamp = System.currentTimeMillis();
            if (x > 3.0) {
                if (stepCallback != null)
                    stepCallback.xDown();
            }
            if (x < -3.0) {
                if (stepCallback != null)
                    stepCallback.xUp();
            }
            if (y > 3.0) {
                if (stepCallback != null)
                    stepCallback.yUp();
            }
            if (y < -1.0) {
                if (stepCallback != null)
                    stepCallback.yDown();
            }
        }
    }

    public void start() {
        sensorManager.registerListener(
                sensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
        );
    }

    public void stop() {
        sensorManager.unregisterListener(
                sensorEventListener,
                sensor
        );
    }
}
