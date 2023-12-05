package com.example.framming;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderIngressos extends RecyclerView.ViewHolder{
    TextView txtNomeFilmeSessao, txtNomeCinemaSessao, txtDatHorSessao, txtSalaSessao, txtCodIng;
    ImageView imgFundoSessao, imgPosterIngresso;

    public MyViewHolderIngressos(@NonNull View itemView) {
        super(itemView);
        txtDatHorSessao = itemView.findViewById(R.id.textView47);
        txtNomeFilmeSessao = itemView.findViewById(R.id.txtNomeFilmeSessao);
        txtNomeCinemaSessao = itemView.findViewById(R.id.txtNomeCinemaSessao);
        imgFundoSessao = itemView.findViewById(R.id.imgFundoSessao);
        txtSalaSessao = itemView.findViewById(R.id.txtSalaSessao);
        imgPosterIngresso = itemView.findViewById(R.id.imgPosterIngresso);
        txtCodIng = itemView.findViewById(R.id.txtCodIng);
    }
}
