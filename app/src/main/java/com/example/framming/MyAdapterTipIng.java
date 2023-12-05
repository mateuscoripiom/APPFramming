package com.example.framming;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.github.muddz.styleabletoast.StyleableToast;

public class MyAdapterTipIng extends RecyclerView.Adapter<MyViewHolderTipIng> {
    private Context tipingcontext;
    private ArrayList<TipoIng> tipingg;
    public static int numIngr = 0;
    public static float valorFIng;
    public static String tipoIngresso;

    Button btnComprar;

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
        float valor = Float.parseFloat(tipingg.get(position).getValorIngresso());
        String upperString = tipingg.get(position).getTipoIngresso().substring(0, 1).toUpperCase() + tipingg.get(position).getTipoIngresso().substring(1).toLowerCase();
        holder.txtTipoIngresso.setText(upperString);
        holder.txtValorIngresso.setText("R$ " + valor);

        holder.btnMais.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(numIngr == 7){
                    StyleableToast.makeText(tipingcontext, "Limite de ingressos por compra atingido!", Toast.LENGTH_LONG, R.style.alertToast).show();
                    holder.btnMais.setAlpha(40);
                    valorFIng = numIngr * Float.parseFloat(tipingg.get(position).getValorIngresso());
                    tipoIngresso = tipingg.get(position).getIdIngresso();
                }
                else {
                    numIngr++;
                    holder.txtQtdTotal.setText(String.valueOf(numIngr));
                    holder.btnMais.setAlpha(255);
                    holder.btnMenos.setAlpha(255);
                    valorFIng = numIngr * Float.parseFloat(tipingg.get(position).getValorIngresso());
                    tipoIngresso = tipingg.get(position).getIdIngresso();
                }
            }
        });

        holder.btnMenos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(numIngr > 0){
                    numIngr--;
                    holder.txtQtdTotal.setText(String.valueOf(numIngr));
                    holder.btnMenos.setAlpha(255);
                    holder.btnMais.setAlpha(255);
                    valorFIng = numIngr * Float.parseFloat(tipingg.get(position).getValorIngresso());
                    tipoIngresso = tipingg.get(position).getIdIngresso();
                }
                if(numIngr == 0){
                    holder.btnMenos.setAlpha(40);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tipingg.size();
    }
}
