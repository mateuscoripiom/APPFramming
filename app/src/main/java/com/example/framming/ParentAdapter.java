package com.example.framming;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {

    Map<String, List<ItemSessaoF>> parentModelClassList;
    Context context;

    public ParentAdapter(Map<String, List<ItemSessaoF>> parentModelClassList, Context context) {
        this.parentModelClassList = parentModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemsessao_view, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapter.ViewHolder holder, int position) {

        ArrayList<String> key = new ArrayList<String>(parentModelClassList.keySet());
        ArrayList<List<ItemSessaoF>> value = new ArrayList<List<ItemSessaoF>>(parentModelClassList.values());

        holder.txtNomeCinemaS.setText(key.get(position));
        ChildAdapter childAdapter;
        childAdapter = new ChildAdapter(value.get(position), context);
        holder.recyclerViewHorarios.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerViewHorarios.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parentModelClassList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerViewHorarios;
        TextView txtNomeCinemaS;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtNomeCinemaS = itemView.findViewById(R.id.txtNomeCinemaS);
            recyclerViewHorarios = itemView.findViewById(R.id.recyclerViewHorarios);
        }
    }
}
