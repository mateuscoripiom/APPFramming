package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterListas extends RecyclerView.Adapter<MyViewHolderListas> {
    private Context listascontext;
    private ArrayList<ListaResponse> itemslista;

    public MyAdapterListas(Context listascontext, ArrayList<ListaResponse> itemslista) {
        this.listascontext = listascontext;
        this.itemslista = itemslista;
    }

    @NonNull
    @Override
    public MyViewHolderListas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderListas(LayoutInflater.from(listascontext).inflate(R.layout.itemlista_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderListas holder, int position) {
        holder.txtNomeLista.setText(itemslista.get(position).getDescricaoLista());
    }

    @Override
    public int getItemCount() {
        return itemslista.size();
    }
}
