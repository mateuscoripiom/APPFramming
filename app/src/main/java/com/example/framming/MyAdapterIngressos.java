package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterIngressos extends RecyclerView.Adapter<MyViewHolderIngressos> {
    private Context ingressoscontext;
    private ArrayList<TicketsFinaisC> ticketsexibido;

    public MyAdapterIngressos(Context ingressoscontext, ArrayList<TicketsFinaisC> ticketsexibido) {
        this.ingressoscontext = ingressoscontext;
        this.ticketsexibido = ticketsexibido;
    }

    @NonNull
    @Override
    public MyViewHolderIngressos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderIngressos(LayoutInflater.from(ingressoscontext).inflate(R.layout.itemingresso_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderIngressos holder, int position) {
        holder.txtNomeCinemaSessao.setText(ticketsexibido.get(position).getNomeCinema());
        holder.txtDatHorSessao.setText(ticketsexibido.get(position).getDataSessao() + " - " + ticketsexibido.get(position).getHorarioSessao());
        holder.txtNomeFilmeSessao.setText(ticketsexibido.get(position).getNomeFilme());
        holder.txtCodIng.setText(ticketsexibido.get(position).getIdFilme());
        holder.txtSalaSessao.setText("Sala: " + ticketsexibido.get(position).getSalaSessao());
        Picasso
                .get()
                .load("https://www.themoviedb.org/t/p/original" + ticketsexibido.get(position).getBackdropFilme())
                .into(holder.imgFundoSessao);
        Picasso
                .get()
                .load("https://www.themoviedb.org/t/p/original" + ticketsexibido.get(position).getPosterFilme())
                .into(holder.imgPosterIngresso);
    }

    @Override
    public int getItemCount() {
        return ticketsexibido.size();
    }
}
