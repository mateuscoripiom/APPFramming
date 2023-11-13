package com.example.framming;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderNac extends RecyclerView.ViewHolder{
    ImageView imgPosterNac;
    public MyViewHolderNac(@NonNull View itemView) {
        super(itemView);
        imgPosterNac = itemView.findViewById(R.id.imgPosterNac);
    }
}
