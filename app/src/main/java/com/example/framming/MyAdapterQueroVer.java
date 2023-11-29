package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterQueroVer extends RecyclerView.Adapter<MyViewHolderQueroVer> {
    private Context querovercontext;
    private ArrayList<ItemQueroVerFinal> itemsqueroverfinal;

    public MyAdapterQueroVer(Context querovercontext, ArrayList<ItemQueroVerFinal> itemsqueroverfinal) {
        this.querovercontext = querovercontext;
        this.itemsqueroverfinal = itemsqueroverfinal;
    }

    @NonNull
    @Override
    public MyViewHolderQueroVer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderQueroVer(LayoutInflater.from(querovercontext).inflate(R.layout.itemquerover_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderQueroVer holder, int position) {
        Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original" + itemsqueroverfinal.get(position).getPoster_path())
                    .into(holder.imgPosterQueroVer);

    }

    @Override
    public int getItemCount() {
        return itemsqueroverfinal.size();
    }
}
