package com.example.framming;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderDiary extends RecyclerView.ViewHolder{
    TextView txtAnoCritica, txtNomeCritica, txtOriginalCritica;
    RatingBar ratingBarCritica;
    ImageView imgPosterCritica;
    public MyViewHolderDiary(@NonNull View itemView) {
        super(itemView);
        imgPosterCritica = itemView.findViewById(R.id.imgPosterCritica);
        txtAnoCritica = itemView.findViewById(R.id.txtAnoCritica);
        txtNomeCritica = itemView.findViewById(R.id.txtNomeCritica);
        txtOriginalCritica = itemView.findViewById(R.id.txtOriginalCritica);
        ratingBarCritica = itemView.findViewById(R.id.ratingBar3);
    }
}
