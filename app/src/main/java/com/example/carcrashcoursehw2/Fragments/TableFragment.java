package com.example.carcrashcoursehw2.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carcrashcoursehw2.Interfaces.CallBack_sendXY;
import com.example.carcrashcoursehw2.R;
import com.example.carcrashcoursehw2.RecyclerView.ScoreList;
import com.example.carcrashcoursehw2.RecyclerView.Score_RecyclerViewAdapter;
import com.example.carcrashcoursehw2.Utilities.sharedPref;
import com.google.gson.Gson;

public class TableFragment extends Fragment {
    private CallBack_sendXY callBack_sendXY;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_table, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.mRecyclerView);


        //no data in recyclerView yet
        String fromJson=sharedPref.getInstance().getString("scores","");
        ScoreList scoreList = new Gson().fromJson(fromJson, ScoreList.class);
        if (scoreList ==null)
            scoreList =new ScoreList();

        Score_RecyclerViewAdapter adapter=new Score_RecyclerViewAdapter(view.getContext(), scoreList.getScores(),callBack_sendXY);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }
    public void setCallBack_sendXY(CallBack_sendXY callBack_sendXY)
    {
        this.callBack_sendXY=callBack_sendXY;
    }
}