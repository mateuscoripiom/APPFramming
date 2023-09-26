package com.example.framming;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderPop extends RecyclerView.ViewHolder{
    ImageView imgPosterPop;
    public MyViewHolderPop(@NonNull View itemView) {
        super(itemView);
        imgPosterPop = itemView.findViewById(R.id.imgPosterPop);
    }
}
