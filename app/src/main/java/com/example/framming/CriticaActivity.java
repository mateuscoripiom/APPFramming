package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CriticaActivity extends AppCompatActivity {

    private TextView txtCriticaUser, txtNomeFilmeCritica, txtAnoFilmeCritica;
    private RatingBar ratingBarCritica;
    private Button btnDataCritica;
    private ImageButton imgbtnvoltar4;
    private ImageView imgPosterCriticaUser, imgFundoCriticaUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critica);

        txtCriticaUser = findViewById(R.id.txtCriticaUser);
        ratingBarCritica = findViewById(R.id.ratingBar2);
        btnDataCritica = findViewById(R.id.btndata2);
        imgPosterCriticaUser = findViewById(R.id.imgPosterCriticaUser);
        txtNomeFilmeCritica = findViewById(R.id.txtName3);
        txtAnoFilmeCritica = findViewById(R.id.txtAno3);
        imgFundoCriticaUser = findViewById(R.id.imgFundo5);
        imgbtnvoltar4 = findViewById(R.id.imgbtnvoltar4);

        imgbtnvoltar4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CriticaActivity.this, HomeActivity.class));
                finish();
            }
        });

        ratingBarCritica.setRating(LogActivity.ratingBar.getRating());
        txtCriticaUser.setText(LogActivity.critica);
        btnDataCritica.setText("Assistido em " + LogActivity.dataAssistido);
        Picasso
                .get()
                .load(LogActivity.imgPosterAssistido)
                .into(imgPosterCriticaUser);
        txtNomeFilmeCritica.setText(LogActivity.nomeFilmeAssitido);
        txtAnoFilmeCritica.setText(LogActivity.anoFilmeAssistido);
        Picasso
                .get()
                .load(LogActivity.imgFundoAssistido)
                .into(imgFundoCriticaUser);

    }
}