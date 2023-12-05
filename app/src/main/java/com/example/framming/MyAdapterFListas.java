package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterFListas extends RecyclerView.Adapter<MyViewHolderFListas> {
    private Context flistascontext;
    private ArrayList<MovieFinal> filmeslista;

    public MyAdapterFListas(Context flistascontext, ArrayList<MovieFinal> filmeslista) {
        this.flistascontext = flistascontext;
        this.filmeslista = filmeslista;
    }

    @NonNull
    @Override
    public MyViewHolderFListas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new MyViewHolderFListas(LayoutInflater.from(flistascontext).inflate(R.layout.itemquerover_view, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderFListas holder, int position) {
        Picasso
                .get()
                .load("https://www.themoviedb.org/t/p/original" + filmeslista.get(position).getPoster_path())
                .into(holder.imgPosterQueroVer);
    }

    @Override
    public int getItemCount() {
        return filmeslista.size();
    }
}
