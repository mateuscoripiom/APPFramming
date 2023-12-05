package com.example.framming;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderListas extends RecyclerView.ViewHolder{
    TextView txtNomeLista;

    public MyViewHolderListas(@NonNull View itemView) {
        super(itemView);
        txtNomeLista = itemView.findViewById(R.id.txtNomeLista);

    }
}
