package com.example.framming;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderSessoes extends RecyclerView.ViewHolder{
    TextView txtNomeCinemaS;
    Button horarioSessoes;
    public MyViewHolderSessoes(@NonNull View itemView) {
        super(itemView);
        txtNomeCinemaS = itemView.findViewById(R.id.txtNomeCinemaS);
        horarioSessoes = itemView.findViewById(R.id.btnhorariosesS);
    }
}
