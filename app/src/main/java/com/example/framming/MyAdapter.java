package com.example.framming;

import static com.example.framming.PosterActivity.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    ArrayList<String> posterArray;
    /*public ArrayList<String> getList(){
        return posterArray;
    }*/


    public MyAdapter(Context context, ArrayList<String> posterArray) {
        this.context = context;
        this.posterArray = posterArray;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso
                .get()
                .load("https://www.themoviedb.org/t/p/original" + posterArray.get(position))
                .into(holder.imgPosterP);
    }

    @Override
    public int getItemCount() {
        return posterArray.size();
    }
}
