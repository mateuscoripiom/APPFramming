package com.example.framming;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderFav extends RecyclerView.ViewHolder{
    ImageView imgPosterQueroVer;
    public MyViewHolderFav(@NonNull View itemView) {
        super(itemView);
        imgPosterQueroVer = itemView.findViewById(R.id.imgPosterFav);
    }
}
