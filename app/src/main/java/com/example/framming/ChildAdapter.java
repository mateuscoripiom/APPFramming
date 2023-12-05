package com.example.framming;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChildAdapter  extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {

    List<ItemSessaoF> childModelClassList;
    Context context;

    public ChildAdapter(List<ItemSessaoF> childModelClassList, Context context) {
        this.childModelClassList = childModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public ChildAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemhorarios_view, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildAdapter.ViewHolder holder, int position) {
        holder.btnHorariosSessoes.setText(childModelClassList.get(position).getHorarioSessao().toString());
        holder.btnHorariosSessoes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                MainActivity.idfilmetiping = childModelClassList.get(position).getIdFilme();
                MainActivity.idsessaotiping = childModelClassList.get(position).getIdSessao();
                MainActivity.tokencintiping = childModelClassList.get(position).getTokenCinema();
                MainActivity.datsessaotiping = childModelClassList.get(position).getDataSessao();
                MainActivity.horsessaotiping = childModelClassList.get(position).getHorarioSessao().toString();
                MainActivity.salatiping = childModelClassList.get(position).getSalaSessao();

                context.startActivity(new Intent(context, tipoingressoActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });
    }

    @Override
    public int getItemCount() {
        return childModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button btnHorariosSessoes;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            btnHorariosSessoes = itemView.findViewById(R.id.btnhorariosesS);
        }
    }
}
