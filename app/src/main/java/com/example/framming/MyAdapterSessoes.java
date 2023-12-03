package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MyAdapterSessoes extends RecyclerView.Adapter<MyViewHolderSessoes> {
    private Context sessoescontext;
    private ArrayList<ItemSessionRVF> sessionsfinalf;

    public MyAdapterSessoes(Context sessoescontext, ArrayList<ItemSessionRVF> sessionsfinalf) {
        this.sessoescontext = sessoescontext;
        this.sessionsfinalf = sessionsfinalf;
    }

    @NonNull
    @Override
    public MyViewHolderSessoes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderSessoes(LayoutInflater.from(sessoescontext).inflate(R.layout.itemsessao_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderSessoes holder, int position) {
        /*LocalDate localDate2 = LocalDate.parse();
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("dd/MMMM/yyyy", new Locale("pt", "BR"));


        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM");*/
        //String dateString = df.format();
        if (MainActivity.posicaoesconder == false){
            // set your date here
            holder.txtNomeCinemaS.setText(sessionsfinalf.get(position).getNomeCinema());
            holder.horarioSessoes.setText(sessionsfinalf.get(position).getHorarioSessao());
        }
        else{
            holder.txtNomeCinemaS.setVisibility(View.GONE);
            holder.horarioSessoes.setText(sessionsfinalf.get(position).getHorarioSessao());
        }

        /*final Map<String, List<Integer>> correlations = new LinkedHashMap<>();
        for (int i = 0; i < sessionsfinalf.size(); i++) {
            final String key = sessionsfinalf.get(i).getNomeCinema();
            if (correlations.containsKey(key)) {
                correlations.get(key).add(i);

                //sessionsfinal.add(new ItemSessionRV());
            } else {
                final List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                holder.txtNomeCinemaS.setText(sessionsfinalf.get(position).getNomeCinema());
                holder.horarioSessoes.setText(sessionsfinalf.get(position).getHorarioSessao());
                correlations.put(key, indexList);
            }
        }*/




    }

    @Override
    public int getItemCount() {
        return sessionsfinalf.size();
    }
}
