package com.example.framming;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView imgPosterP;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imgPosterP = itemView.findViewById(R.id.imgPosterP);
    }
}
