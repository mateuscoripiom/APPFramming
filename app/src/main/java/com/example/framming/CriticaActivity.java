package com.example.framming;

import static com.example.framming.HomeActivity.items;
import static com.example.framming.HomeActivity.swtPosition;
import static com.example.framming.PesquisaActivity.itemsbusca;
import static com.example.framming.PosterActivity.posterArray;

import androidx.activity.OnBackPressedCallback;
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

    private TextView txtCriticaUser, txtNomeFilmeCritica, txtAnoFilmeCritica, txtPerfilCritica;
    private RatingBar ratingBarCritica;
    private Button btnDataCritica;
    private ImageButton imgbtnvoltar4;
    private ImageView imgPosterCriticaUser, imgFundoCriticaUser, imgPerfilCritica;

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
        txtPerfilCritica = findViewById(R.id.txtPerfilCritica);
        imgPerfilCritica = findViewById(R.id.imgPerfilCritica);

        txtPerfilCritica.setText(HomeActivity.nickusuario);
        Picasso
                .get()
                .load(HomeActivity.iconusuario)
                .into(imgPerfilCritica);

        imgbtnvoltar4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CriticaActivity.this, HomeActivity.class));
                finish();
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(CriticaActivity.this, HomeActivity.class));
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        if(DiaryActivity.diarioCritica == true){
            ratingBarCritica.setRating(DiaryActivity.notaCritica);
            txtCriticaUser.setText(DiaryActivity.textoCritica);
            btnDataCritica.setText("Assistido em " + DiaryActivity.dataCritica);
            Picasso
                    .get()
                    .load(DiaryActivity.imgposterCritica)
                    .into(imgPosterCriticaUser);
            txtNomeFilmeCritica.setText(DiaryActivity.nomefilmeCritica);
            txtAnoFilmeCritica.setText(DiaryActivity.anoCritica);
            Picasso
                    .get()
                    .load(DiaryActivity.imgfundoCritica)
                    .into(imgFundoCriticaUser);
        }
        else {

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
}