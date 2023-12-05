package com.example.framming;

import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderTipIng extends RecyclerView.ViewHolder{
    TextView txtTipoIngresso, txtValorIngresso, txtQtdTotal;
    ImageView btnMenos, btnMais;
    public MyViewHolderTipIng(@NonNull View itemView) {
        super(itemView);
        txtTipoIngresso = itemView.findViewById(R.id.txtTipoIngresso);
        txtValorIngresso = itemView.findViewById(R.id.txtValorIngresso);
        btnMais = itemView.findViewById(R.id.imgMais);
        btnMenos = itemView.findViewById(R.id.imgMenos);
        txtQtdTotal = itemView.findViewById(R.id.txtQtdTotal);

    }
}
