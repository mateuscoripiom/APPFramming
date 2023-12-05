package com.example.framming;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MyAdapterDatas extends RecyclerView.Adapter<MyViewHolderDatas> {
    private Context datascontext;
    private Map<String, ArrayList<ItemSession>> correlations3;
    int row_index = 0;

    public MyAdapterDatas(Context datascontext, Map<String, ArrayList<ItemSession>> correlations3) {
        this.datascontext = datascontext;
        this.correlations3 = correlations3;
    }

    @NonNull
    @Override
    public MyViewHolderDatas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderDatas(LayoutInflater.from(datascontext).inflate(R.layout.itemdata_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderDatas holder, int position) {
        ArrayList<String> key = new ArrayList<String>(correlations3.keySet());
        ArrayList<ArrayList<ItemSession>> value = new ArrayList<ArrayList<ItemSession>>(correlations3.values());

        holder.btnDatas.setText(key.get(position));

    }


    @Override
    public int getItemCount() {
        return correlations3.size();
    }
}
