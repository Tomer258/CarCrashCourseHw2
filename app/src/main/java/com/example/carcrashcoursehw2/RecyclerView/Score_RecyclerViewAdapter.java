package com.example.carcrashcoursehw2.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carcrashcoursehw2.R;
import com.google.gson.Gson;
import com.paz.prefy_lib.Prefy;

import java.util.ArrayList;

public class Score_RecyclerViewAdapter extends RecyclerView.Adapter<Score_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<ScoreModel> scores;
    public Score_RecyclerViewAdapter(Context context, ArrayList<ScoreModel> scores)
    {
        this.context=context;
        this.scores=scores;

    }

    @NonNull
    @Override
    public Score_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_item,parent,false);
        return new Score_RecyclerViewAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Score_RecyclerViewAdapter.MyViewHolder holder, int position) {
        ScoreModel model=getItem(position);
        holder.scoreView.setText(model.getScore());
        holder.dateView.setText(model.getDate());
        holder.distanceView.setText(model.getDistance());
    }

    @Override
    public int getItemCount() {
        return this.scores==null ? 0:this.scores.size();
    }
    public ScoreModel getItem(int position)
    {
        return this.scores.get(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView scoreView,dateView,distanceView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            scoreView=itemView.findViewById(R.id.num);
            dateView=itemView.findViewById(R.id.date);
            distanceView=itemView.findViewById(R.id.numOfDis);
        }
    }
}
