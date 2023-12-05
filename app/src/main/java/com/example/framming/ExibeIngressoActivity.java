package com.example.framming;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                CadIngressosActivity.ticketsexibido.clear();
                CadIngressosActivity.tickets.clear();
                CadIngressosActivity.ticketsfinais.clear();
                CadIngressosActivity.ticketsFinaisCS.clear();
                CadIngressosActivity.ticketsFinaisSES.clear();
                startActivity(new Intent(ExibeIngressoActivity.this, CadIngressosActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}