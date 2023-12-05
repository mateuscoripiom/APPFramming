package com.example.framming;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderCadIng extends RecyclerView.ViewHolder{
    TextView txtNomeFilmeSessao, txtNomeCinemaSessao, txtDatHorSessao;
    ImageView imgFundoSessao;

    public MyViewHolderCadIng(@NonNull View itemView) {
        super(itemView);
        txtDatHorSessao = itemView.findViewById(R.id.textView47);
        txtNomeFilmeSessao = itemView.findViewById(R.id.txtNomeFilmeSessao);
        txtNomeCinemaSessao = itemView.findViewById(R.id.txtNomeCinemaSessao);
        imgFundoSessao = itemView.findViewById(R.id.imgFundoSessao);
    }
}
