package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MyAdapterDatas extends RecyclerView.Adapter<MyViewHolderDatas> {
    private Context datascontext;
    private ArrayList<ItemSession> datasiguais;

    public MyAdapterDatas(Context datascontext, ArrayList<ItemSession> datasiguais) {
        this.datascontext = datascontext;
        this.datasiguais = datasiguais;
    }

    @NonNull
    @Override
    public MyViewHolderDatas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderDatas(LayoutInflater.from(datascontext).inflate(R.layout.itemdata_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderDatas holder, int position) {
        holder.btnDatas.setText(datasiguais.get(position).getDataSessao());



    }

    @Override
    public int getItemCount() {
        return datasiguais.size();
    }
}
