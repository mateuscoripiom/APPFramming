package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class tipoingressoActivity extends AppCompatActivity {

    Integer numIng;
    ImageView btnmenos, btnmais;
    TextView txtnumIng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_tipoingresso);

        btnmenos = findViewById(R.id.imageView17);
        btnmais = findViewById(R.id.imageView19);

        txtnumIng = findViewById(R.id.textView30);



        btnmais.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numIng++;
                txtnumIng.setText(numIng);
            }
        });

        btnmenos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                int i = numIng - 1;
                txtnumIng.setText(i);
            }
        });


    }
}