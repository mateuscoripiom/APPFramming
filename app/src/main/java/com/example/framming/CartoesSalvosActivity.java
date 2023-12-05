package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class CartoesSalvosActivity extends AppCompatActivity {

    RecyclerView recyclerViewCartoesS;
    Button btnCadCartao;

    public static String numCartao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoes_salvos);
        recyclerViewCartoesS = findViewById(R.id.recyclerViewCartoesS);
        btnCadCartao = findViewById(R.id.btnCadCartao);

        btnCadCartao.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CartoesSalvosActivity.this, creditoActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

        MyAdapterCartoesS myAdapterCartoesS = new MyAdapterCartoesS(CartoesSalvosActivity.this, formadepagamentoActivity.itemspagamento);
        LinearLayoutManager manager = new LinearLayoutManager(CartoesSalvosActivity.this, recyclerViewCartoesS.VERTICAL, false);
        recyclerViewCartoesS.setLayoutManager(manager);
        recyclerViewCartoesS.setAdapter(myAdapterCartoesS);

        recyclerViewCartoesS.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerViewCartoesS, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        numCartao = formadepagamentoActivity.itemspagamento.get(position).getNumeroCartao();
                        startActivity(new Intent(CartoesSalvosActivity.this, ConfirmaNumCard.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }

                    @Override/*IDPopUp = items.get(position).getIdpop();
                                startActivity(new Intent(HomeActivity.this, PopUpActivity.class));*/
                    public void onLongItemClick(View view, int position) {

                        //Createpopupwindows();
                    }
                })
        );
    }
}