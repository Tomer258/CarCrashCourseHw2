package com.example.carcrashcoursehw2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carcrashcoursehw2.R;
import com.example.carcrashcoursehw2.RecyclerView.ScoreModel;
import com.example.carcrashcoursehw2.RecyclerView.Score_RecyclerViewAdapter;

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
        Score_RecyclerViewAdapter adapter=new Score_RecyclerViewAdapter(view.getContext(),scores);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return inflater.inflate(R.layout.fragment_table, container, false);
    }
}