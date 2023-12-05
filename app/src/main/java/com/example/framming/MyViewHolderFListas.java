package com.example.framming;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderFListas extends RecyclerView.ViewHolder{
    ImageView imgPosterQueroVer;

    public MyViewHolderFListas(@NonNull View itemView) {
        super(itemView);
        imgPosterQueroVer = itemView.findViewById(R.id.imgPosterQueroVer);
    }
}
