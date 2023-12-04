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
import java.util.List;
import java.util.Locale;

public class MyAdapterTipIng extends RecyclerView.Adapter<MyViewHolderTipIng> {
    private Context tipingcontext;
    private ArrayList<TipoIng> tipingg;

    public MyAdapterTipIng(Context tipingcontext, ArrayList<TipoIng> tipingg) {
        this.tipingcontext = tipingcontext;
        this.tipingg = tipingg;
    }

    @NonNull
    @Override
    public MyViewHolderTipIng onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderTipIng(LayoutInflater.from(tipingcontext).inflate(R.layout.itemtiping_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderTipIng holder, int position) {
        holder.txtTipoIngresso.setText(tipingg.get(position).getTipoIngresso());
        holder.txtValorIngresso.setText(tipingg.get(position).getValorIngresso());


    }

    @Override
    public int getItemCount() {
        return tipingg.size();
    }
}
