package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterCadIng extends RecyclerView.Adapter<MyViewHolderCadIng> {
    private Context cadingcontext;
    private ArrayList<TicketsFinaisC> ticketsFinaisCS;

    public MyAdapterCadIng(Context cadingcontext, ArrayList<TicketsFinaisC> ticketsFinaisCS) {
        this.cadingcontext = cadingcontext;
        this.ticketsFinaisCS = ticketsFinaisCS;
    }

    @NonNull
    @Override
    public MyViewHolderCadIng onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderCadIng(LayoutInflater.from(cadingcontext).inflate(R.layout.itemcading_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderCadIng holder, int position) {
        holder.txtNomeCinemaSessao.setText(ticketsFinaisCS.get(position).getNomeCinema());
        holder.txtDatHorSessao.setText(ticketsFinaisCS.get(position).getDataSessao() + " - " + ticketsFinaisCS.get(position).getHorarioSessao());
        holder.txtNomeFilmeSessao.setText(ticketsFinaisCS.get(position).getNomeFilme());
        Picasso
                .get()
                .load("https://www.themoviedb.org/t/p/original" + ticketsFinaisCS.get(position).getBackdropFilme())
                .into(holder.imgFundoSessao);
    }

    @Override
    public int getItemCount() {
        return ticketsFinaisCS.size();
    }
}
