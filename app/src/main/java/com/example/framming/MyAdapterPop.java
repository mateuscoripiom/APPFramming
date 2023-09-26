package com.example.framming;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterPop extends RecyclerView.Adapter<MyViewHolderPop> {
    Context popcontext;
    List<Item> items;

    public MyAdapterPop(Context popcontext, List<Item> items) {
        this.popcontext = popcontext;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolderPop onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderPop(LayoutInflater.from(popcontext).inflate(R.layout.itempop_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderPop holder, int position) {
        Picasso
                .get()
                .load("https://www.themoviedb.org/t/p/original" + items.get(position).getImage())
                .into(holder.imgPosterPop);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
