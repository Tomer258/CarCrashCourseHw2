package com.example.carcrashcoursehw2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carcrashcoursehw2.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapFragment extends Fragment {
    private GoogleMap gMap;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_map,container,false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        assert supportMapFragment != null;
        supportMapFragment.getMapAsync(googleMap -> gMap = googleMap);
        return view;
    }

    public void showUserLocation(double x,double y) {
        gMap.clear();
        LatLng point = new LatLng(x,y);
        gMap.addMarker(new MarkerOptions()
                .position(point));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(point));
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(point, 17.0f));
    }
}