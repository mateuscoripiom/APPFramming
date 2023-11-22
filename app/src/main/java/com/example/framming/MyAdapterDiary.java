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

public class MyAdapterDiary extends RecyclerView.Adapter<MyViewHolderDiary> {
    private Context diarycontext;
    private ArrayList<ItemFeedbackF> itemsffinal;

    public MyAdapterDiary(Context diarycontext, ArrayList<ItemFeedbackF> itemsffinal) {
        this.diarycontext = diarycontext;
        this.itemsffinal = itemsffinal;
    }

    @NonNull
    @Override
    public MyViewHolderDiary onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderDiary(LayoutInflater.from(diarycontext).inflate(R.layout.itemdiario_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderDiary holder, int position) {
        holder.txtNomeCritica.setText(itemsffinal.get(position).getNomeFilme());
        holder.txtAnoCritica.setText(itemsffinal.get(position).getAnoFilme());
        holder.txtOriginalCritica.setText(itemsffinal.get(position).getOriginalFilme());
        holder.ratingBarCritica.setRating(itemsffinal.get(position).getNotaCritica());
        LocalDate localDate = LocalDate.parse(itemsffinal.get(position).getDataCritica());
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy", new Locale("pt", "BR"));
        holder.txtDataC.setText(localDate.format(dateTimeFormatter));
        Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original" + itemsffinal.get(position).getPosterFilme())
                    .into(holder.imgPosterCritica);

    }

    @Override
    public int getItemCount() {
        return itemsffinal.size();
    }
}
