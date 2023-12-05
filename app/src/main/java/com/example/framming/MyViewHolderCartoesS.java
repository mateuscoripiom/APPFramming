package com.example.framming;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderCartoesS extends RecyclerView.ViewHolder{
    Button btnNumCard;
    public MyViewHolderCartoesS(@NonNull View itemView) {
        super(itemView);
        btnNumCard = itemView.findViewById(R.id.btnNumCard);
    }
}
