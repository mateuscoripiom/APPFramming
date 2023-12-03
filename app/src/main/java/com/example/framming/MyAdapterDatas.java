package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
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
    private ArrayList<ItemSession> sessionsdata;

    public MyAdapterDatas(Context datascontext, ArrayList<ItemSession> sessionsdata) {
        this.datascontext = datascontext;
        this.sessionsdata = sessionsdata;
    }

    @NonNull
    @Override
    public MyViewHolderDatas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderDatas(LayoutInflater.from(datascontext).inflate(R.layout.itemdata_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderDatas holder, int position) {
        /*LocalDate localDate2 = LocalDate.parse();
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("dd/MMMM/yyyy", new Locale("pt", "BR"));


        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM");*/
        //String dateString = df.format();

        holder.btnDatas.setText(sessionsdata.get(position).getDataSessao());

    }

    @Override
    public int getItemCount() {
        return sessionsdata.size();
    }
}
