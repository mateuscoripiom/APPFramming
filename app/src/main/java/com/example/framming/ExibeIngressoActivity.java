package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ExibeIngressoActivity extends AppCompatActivity {

    RecyclerView recyclerViewIngressos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibe_ingresso);

        recyclerViewIngressos = findViewById(R.id.recyclerViewIngressos);


        MyAdapterIngressos myAdapterIngressos = new MyAdapterIngressos(ExibeIngressoActivity.this, CadIngressosActivity.ticketsexibido);
        LinearLayoutManager manager = new LinearLayoutManager(ExibeIngressoActivity.this, recyclerViewIngressos.HORIZONTAL, false);
        recyclerViewIngressos.setLayoutManager(manager);
        recyclerViewIngressos.setAdapter(myAdapterIngressos);
    }
}