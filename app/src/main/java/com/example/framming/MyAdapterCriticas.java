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

public class MyAdapterCriticas extends RecyclerView.Adapter<MyViewHolderCriticas> {
    private Context criticascontext;
    private ArrayList<ItemCriticaFinal> criticasfilmefinal;

    public MyAdapterCriticas(Context criticascontext, ArrayList<ItemCriticaFinal> criticasfilmefinal) {
        this.criticascontext = criticascontext;
        this.criticasfilmefinal = criticasfilmefinal;
    }

    @NonNull
    @Override
    public MyViewHolderCriticas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderCriticas(LayoutInflater.from(criticascontext).inflate(R.layout.itemcritica_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderCriticas holder, int position) {
        holder.txtTextCritica.setText(criticasfilmefinal.get(position).getTextoCritica());
        holder.txtPerfilCr.setText(criticasfilmefinal.get(position).getNickUsuario());
        holder.ratingBarCriticaMain.setRating(criticasfilmefinal.get(position).getNotaCritica());
        Picasso
                    .get()
                    .load(criticasfilmefinal.get(position).getIconUsuario())
                    .into(holder.imgCriticaPerf);

    }

    @Override
    public int getItemCount() {
        return criticasfilmefinal.size();
    }
}
