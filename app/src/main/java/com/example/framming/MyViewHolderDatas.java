package com.example.framming;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderDatas extends RecyclerView.ViewHolder{
    Button btnDatas;
    public MyViewHolderDatas(@NonNull View itemView) {
        super(itemView);
        btnDatas = itemView.findViewById(R.id.btndatas);
    }
}
