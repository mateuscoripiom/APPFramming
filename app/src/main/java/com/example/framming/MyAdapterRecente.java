package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class MyAdapterRecente extends RecyclerView.Adapter<MyViewHolderRecente> {
    private Context recentecontext;
    private ArrayList<ItemFeedbackFRecente> itemsffinalrecente;

    public MyAdapterRecente(Context recentecontext, ArrayList<ItemFeedbackFRecente> itemsffinalrecente) {
        this.recentecontext = recentecontext;
        this.itemsffinalrecente = itemsffinalrecente;
    }

    @NonNull
    @Override
    public MyViewHolderRecente onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderRecente(LayoutInflater.from(recentecontext).inflate(R.layout.itemrecente_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderRecente holder, int position) {
        holder.ratingBarRecente.setRating(itemsffinalrecente.get(position).getNotaCritica());
        Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original" + itemsffinalrecente.get(position).getPosterFilme())
                    .into(holder.imgPosterRecente);

    }

    @Override
    public int getItemCount() {
        return itemsffinalrecente.size();
    }
}
