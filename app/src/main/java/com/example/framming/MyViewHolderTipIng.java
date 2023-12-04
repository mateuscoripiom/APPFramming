package com.example.framming;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderTipIng extends RecyclerView.ViewHolder{
    TextView txtTipoIngresso, txtValorIngresso;
    public MyViewHolderTipIng(@NonNull View itemView) {
        super(itemView);
        txtTipoIngresso = itemView.findViewById(R.id.txtTipoIngresso);
        txtValorIngresso = itemView.findViewById(R.id.txtValorIngresso);
    }
}
