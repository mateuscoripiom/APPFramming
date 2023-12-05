package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterCartoesS extends RecyclerView.Adapter<MyViewHolderCartoesS> {
    Context cartoescontext;
    ArrayList<PaymentResponse> itemspagamento;

    public MyAdapterCartoesS(Context cartoescontext, ArrayList<PaymentResponse> itemspagamento) {
        this.cartoescontext = cartoescontext;
        this.itemspagamento = itemspagamento;
    }

    @NonNull
    @Override
    public MyViewHolderCartoesS onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderCartoesS(LayoutInflater.from(cartoescontext).inflate(R.layout.itemcardsalvo_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderCartoesS holder, int position) {
        holder.btnNumCard.setText(itemspagamento.get(position).getNumeroCartao().substring(0,4) + "************");

    }

    @Override
    public int getItemCount() {
        return itemspagamento.size();
    }
}
