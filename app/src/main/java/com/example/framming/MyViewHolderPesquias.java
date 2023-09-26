package com.example.framming;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderPesquias extends RecyclerView.ViewHolder{
    TextView txtNomeBusca, txtOriginalBusca, txtAnoBusca, txtSinopseBusca;
    ImageView imgPosterBusca;
    public MyViewHolderPesquias(@NonNull View itemView) {
        super(itemView);
        txtNomeBusca = itemView.findViewById(R.id.txtNomeBusca);
        txtOriginalBusca = itemView.findViewById(R.id.txtOriginalBusca);
        txtAnoBusca = itemView.findViewById(R.id.txtAnoBusca);
        txtSinopseBusca = itemView.findViewById(R.id.txtSinopseBusca);
        imgPosterBusca = itemView.findViewById(R.id.imgPosterBusca);
    }
}
