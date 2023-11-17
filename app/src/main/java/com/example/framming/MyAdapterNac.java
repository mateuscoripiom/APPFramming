package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterNac extends RecyclerView.Adapter<MyViewHolderNac> {
    private Context naccontext;
    private ArrayList<ItemNac> itemsnac;

    public MyAdapterNac(Context naccontext, ArrayList<ItemNac> itemsnac) {
        this.naccontext = naccontext;
        this.itemsnac = itemsnac;
    }

    @NonNull
    @Override
    public MyViewHolderNac onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderNac(LayoutInflater.from(naccontext).inflate(R.layout.itemnac_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNac holder, int position) {
        Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original" + itemsnac.get(position).getPoster_path())
                    .into(holder.imgPosterNac);

    }

    @Override
    public int getItemCount() {
        return itemsnac.size();
    }
}
