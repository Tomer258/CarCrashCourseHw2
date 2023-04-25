package com.example.carcrashcoursehw2.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carcrashcoursehw2.Interfaces.CallBack_sendXY;
import com.example.carcrashcoursehw2.R;
import com.google.gson.Gson;
import com.paz.prefy_lib.Prefy;

import java.util.ArrayList;

public class Score_RecyclerViewAdapter extends RecyclerView.Adapter<Score_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<ScoreModel> scores;
    CallBack_sendXY callBack_sendXY;
    public Score_RecyclerViewAdapter(Context context, ArrayList<ScoreModel> scores,CallBack_sendXY callBack_sendXY)
    {
        this.context=context;
        this.scores=scores;
        this.callBack_sendXY=callBack_sendXY;

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
        holder.distanceView.setText(scores.get(position).getDistance()+"");
        holder.posView.setText((position+1)+"");
        holder.mCardView.setOnClickListener(view -> {
            double x=scores.get(position).getX();
            double y=scores.get(position).getY();
            if (callBack_sendXY!=null)
                callBack_sendXY.sendXy(x,y);
        });

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

        TextView scoreView,dateView,distanceView,posView;
        CardView mCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            scoreView=itemView.findViewById(R.id.num);
            dateView=itemView.findViewById(R.id.date);
            distanceView=itemView.findViewById(R.id.numOfDis);
            posView=itemView.findViewById(R.id.pos);
            mCardView=itemView.findViewById(R.id.mCardView);
        }
    }
}
