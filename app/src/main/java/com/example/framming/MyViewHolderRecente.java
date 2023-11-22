package com.example.framming;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderRecente extends RecyclerView.ViewHolder{
    RatingBar ratingBarRecente;
    ImageView imgPosterRecente;
    public MyViewHolderRecente(@NonNull View itemView) {
        super(itemView);
        imgPosterRecente = itemView.findViewById(R.id.imgPosterRecente);
        ratingBarRecente = itemView.findViewById(R.id.ratingBarRecente);
    }
}
