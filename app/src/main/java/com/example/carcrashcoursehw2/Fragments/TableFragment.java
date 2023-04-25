package com.example.carcrashcoursehw2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carcrashcoursehw2.R;
import com.example.carcrashcoursehw2.RecyclerView.ScoreModel;
import com.example.carcrashcoursehw2.RecyclerView.Score_RecyclerViewAdapter;
import com.example.carcrashcoursehw2.Utilities.sharedPref;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paz.prefy_lib.Prefy;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TableFragment extends Fragment {
    ArrayList<ScoreModel> scores =new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.mRecyclerView);
        //no data in recyclerView yet
        ArrayList<ScoreModel> test=new ArrayList<>();
        this.scores=Prefy.getInstance().getArrayList("scores", new ArrayList<ScoreModel>());
        Score_RecyclerViewAdapter adapter=new Score_RecyclerViewAdapter(view.getContext(),scores);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }
}