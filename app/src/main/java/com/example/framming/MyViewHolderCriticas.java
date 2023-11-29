package com.example.framming;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderCriticas extends RecyclerView.ViewHolder{
    TextView txtPerfilCr, txtTextCritica;
    RatingBar ratingBarCriticaMain;
    ImageView imgCriticaPerf;
    public MyViewHolderCriticas(@NonNull View itemView) {
        super(itemView);
        txtPerfilCr = itemView.findViewById(R.id.txtPerfilCr);
        txtTextCritica = itemView.findViewById(R.id.txtTextCritica);
        ratingBarCriticaMain = itemView.findViewById(R.id.ratingBar5);
        imgCriticaPerf = itemView.findViewById(R.id.imgCriticaPerf);
    }
}
