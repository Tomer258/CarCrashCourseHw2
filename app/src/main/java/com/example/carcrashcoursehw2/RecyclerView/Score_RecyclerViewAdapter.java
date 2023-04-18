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
        holder.scoreView.setText(scores.get(position).getScore()+"");
        holder.dateView.setText(scores.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return scores.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView scoreView,dateView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            scoreView=itemView.findViewById(R.id.num);
            dateView=itemView.findViewById(R.id.date);
        }
    }
}
