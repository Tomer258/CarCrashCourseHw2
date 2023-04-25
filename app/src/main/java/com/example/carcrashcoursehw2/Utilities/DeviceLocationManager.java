package com.example.carcrashcoursehw2.Utilities;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;

public class DeviceLocationManager {
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private Context context;
    private static DeviceLocationManager instance = null;
    private static FusedLocationProviderClient fusedLocationClient;
    private static double x = 0, y = 0;
    private static LocationRequest locationRequest;
    private static LocationCallback locationCallback;

    private DeviceLocationManager(Context context) {
        this.context = context;
    }

    public static DeviceLocationManager getInstance() {
        return instance;
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = new DeviceLocationManager(context);
        }
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        locationRequest = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000)
                .setWaitForAccurateLocation(false)
                .setMinUpdateIntervalMillis(500)
                .setMaxUpdateDelayMillis(1000)
                .build();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    x = location.getLatitude();
                    y = location.getLongitude();
                }
            }
        };
    }
    public void startLocationUpdates(Activity activity) {
       checkLocationPermission(activity);
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    public void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback);
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }


    public boolean checkLocationPermission(Activity activity)
    {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (ContextCompat.checkSelfPermission(context.getApplicationContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)
            {
            }
            else
            {
                ActivityCompat.requestPermissions(activity,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            }
        }

        return  true;
    }
    public boolean isGPSOn() {
        return ((LocationManager) context.getSystemService(LOCATION_SERVICE)).isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

}

