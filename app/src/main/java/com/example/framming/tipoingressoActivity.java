package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import io.github.muddz.styleabletoast.StyleableToast;

public class tipoingressoActivity extends AppCompatActivity {

    Integer numIng = 0;
    ImageView btnmenos, btnmais, imgPosterTipoIng;
    TextView txtnumIng, txtNomeFilmeTipIng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_tipoingresso);

        btnmenos = findViewById(R.id.imageView17);
        btnmais = findViewById(R.id.imageView19);
        imgPosterTipoIng = findViewById(R.id.imgPosterTipoIng);
        txtNomeFilmeTipIng = findViewById(R.id.txtNomeFilmeTipIng);

        txtNomeFilmeTipIng.setText(MainActivity.nomeFilmeTipIng);
        Picasso
                .get()
                .load("https://www.themoviedb.org/t/p/original" + MainActivity.posterFilmeTipIng)
                .into(imgPosterTipoIng);


        txtnumIng = findViewById(R.id.textView30);

        btnmenos.setAlpha(40);

        btnmais.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(numIng == 7){
                    StyleableToast.makeText(tipoingressoActivity.this, "Limite de ingressos por compra atingido!", Toast.LENGTH_LONG, R.style.alertToast).show();
                    btnmais.setAlpha(40);
                }
                else {
                    numIng++;
                    txtnumIng.setText(String.valueOf(numIng));
                    btnmais.setAlpha(255);
                    btnmenos.setAlpha(255);
                }
            }
        });

        btnmenos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(numIng > 0){
                    numIng--;
                    txtnumIng.setText(String.valueOf(numIng));
                    btnmenos.setAlpha(255);
                    btnmais.setAlpha(255);
                }
                if(numIng == 0){
                    btnmenos.setAlpha(40);
                }
            }
        });
    }


}