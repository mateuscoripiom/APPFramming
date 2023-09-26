package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterPesquisa extends RecyclerView.Adapter<MyViewHolderPesquias> {
    Context context;
    List<ItemBusca> itemsbusca;


    public MyAdapterPesquisa(Context context, List<ItemBusca> itemsbusca) {
        this.context = context;
        this.itemsbusca = itemsbusca;
    }

    @NonNull
    @Override
    public MyViewHolderPesquias onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderPesquias(LayoutInflater.from(context).inflate(R.layout.itembusca_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderPesquias holder, int position) {
        holder.txtNomeBusca.setText(itemsbusca.get(position).getNamebusca());
        holder.txtSinopseBusca.setText(itemsbusca.get(position).getOverview());
        holder.txtAnoBusca.setText(itemsbusca.get(position).getAnobusca());
        holder.txtOriginalBusca.setText(itemsbusca.get(position).getOriginalname());
        Picasso
                .get()
                .load("https://www.themoviedb.org/t/p/original" + itemsbusca.get(position).getImgbusca())
                .into(holder.imgPosterBusca);
    }

    @Override
    public int getItemCount() {
        return itemsbusca.size();
    }
}
