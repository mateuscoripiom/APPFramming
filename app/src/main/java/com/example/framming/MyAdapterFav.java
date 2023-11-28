package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterFav extends RecyclerView.Adapter<MyViewHolderFav> {
    private Context favcontext;
    private ArrayList<ItemFavFinal> itemsfavfinal;

    public MyAdapterFav(Context favcontext, ArrayList<ItemFavFinal> itemsfavfinal) {
        this.favcontext = favcontext;
        this.itemsfavfinal = itemsfavfinal;
    }

    @NonNull
    @Override
    public MyViewHolderFav onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderFav(LayoutInflater.from(favcontext).inflate(R.layout.itemfav_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderFav holder, int position) {
        Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original" + itemsfavfinal.get(position).getPosterFilme())
                    .into(holder.imgPosterQueroVer);

    }

    @Override
    public int getItemCount() {
        return itemsfavfinal.size();
    }
}
