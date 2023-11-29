package com.example.framming;

import static com.example.framming.HomeActivity.items;
import static com.example.framming.HomeActivity.swtPosition;
import static com.example.framming.PesquisaActivity.itemsbusca;
import static com.example.framming.PosterActivity.posterArray;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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



        imgbtnvoltar4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DiaryActivity.itemsffinal.clear();
                DiaryActivity.itemsfeedback.clear();
                DiaryActivity.contagemdiario = 0;
                HomeActivity.ID = null;
                HomeActivity.IDPositionPop = null;
                HomeActivity.itemsfinal.clear();
                HomeActivity.items.clear();
                PosterActivity.IDPosition = null;
                MainActivity.usado = false;
                MainActivity.usadoEscolha = false;
                QueroVerActivity.contagemquerover = 0;
                MainActivity.linkFilmeSalvo = null;
                MainActivity.listadoQV = false;
                PesquisaActivity.IDpesquisa = null;
                HomeActivity.usadobtn = 0;
                posterArray.clear();
                itemsbusca.clear();
                MainActivity.usoMainUsu = false;
                MainActivity.linkFilmeSalvo = null;
                items.clear();
                swtPosition = false;
                MainActivity.criticasfilme.clear();
                MainActivity.criticasfilmefinal.clear();
                ProfileActivity.itemsffinalrecente.clear();
                ProfileActivity.itemsfav.clear();
                ProfileActivity.itemsfavfinal.clear();
                if(DiaryActivity.usoDiario == true){
                    DiaryActivity.usoDiario = false;
                    startActivity(new Intent(CriticaActivity.this, DiaryActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    finish();
                }
                else {
                    startActivity(new Intent(CriticaActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    finish();
                }
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                DiaryActivity.itemsffinal.clear();
                DiaryActivity.itemsfeedback.clear();
                DiaryActivity.contagemdiario = 0;
                ProfileActivity.itemsffinalrecente.clear();
                HomeActivity.ID = null;
                HomeActivity.IDPositionPop = null;
                PosterActivity.IDPosition = null;
                MainActivity.usado = false;
                MainActivity.usadoEscolha = false;
                QueroVerActivity.contagemquerover = 0;
                HomeActivity.itemsfinal.clear();
                HomeActivity.items.clear();
                MainActivity.linkFilmeSalvo = null;
                MainActivity.listadoQV = false;
                PesquisaActivity.IDpesquisa = null;
                HomeActivity.usadobtn = 0;
                posterArray.clear();
                itemsbusca.clear();
                MainActivity.usoMainUsu = false;
                MainActivity.linkFilmeSalvo = null;
                items.clear();
                swtPosition = false;
                MainActivity.criticasfilme.clear();
                MainActivity.criticasfilmefinal.clear();
                if(DiaryActivity.usoDiario == true){
                    DiaryActivity.usoDiario = false;
                    startActivity(new Intent(CriticaActivity.this, DiaryActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    finish();
                }
                else {
                    startActivity(new Intent(CriticaActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        if(DiaryActivity.usoDiario == true){
            LocalDate localDate = LocalDate.parse(DiaryActivity.dataCritica);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy", new Locale("pt", "BR"));
            ratingBarCritica.setRating(DiaryActivity.notaCritica);
            txtCriticaUser.setText(DiaryActivity.textoCritica);
            btnDataCritica.setText("Assistido em " + localDate.format(dateTimeFormatter));
            Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original" + DiaryActivity.imgposterCritica)
                    .into(imgPosterCriticaUser);
            txtNomeFilmeCritica.setText(DiaryActivity.nomefilmeCritica);
            txtAnoFilmeCritica.setText(DiaryActivity.anoCritica);
            Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original" + DiaryActivity.imgfundoCritica)
                    .into(imgFundoCriticaUser);
            txtPerfilCritica.setText(HomeActivity.nickusuario);
            Picasso
                    .get()
                    .load(HomeActivity.iconusuario)
                    .into(imgPerfilCritica);
            imgPosterCriticaUser.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    HomeActivity.IDPositionPop = DiaryActivity.idFilmeMain;
                    startActivity(new Intent(CriticaActivity.this, MainActivity.class));
                    finish();
                }
            });
        }
        else if(MainActivity.usoMainUsu == true){
            LocalDate localDate = LocalDate.parse(MainActivity.dataCriticaUsu);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy", new Locale("pt", "BR"));
            ratingBarCritica.setRating(MainActivity.notaCriticaUsu);
            txtCriticaUser.setText(MainActivity.textoCriticaUsu);
            btnDataCritica.setText("Assistido em " + localDate.format(dateTimeFormatter));
            Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original" + MainActivity.posterFilmeUsu)
                    .into(imgPosterCriticaUser);
            txtNomeFilmeCritica.setText(MainActivity.nomefilmeUsu);
            txtAnoFilmeCritica.setText(MainActivity.dataFilmeUsu);
            Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original" + MainActivity.fundoFilmeUsu)
                    .into(imgFundoCriticaUser);
            txtPerfilCritica.setText(MainActivity.nickUsu);
            Picasso
                    .get()
                    .load(MainActivity.imgPerfilUsu)
                    .into(imgPerfilCritica);
            imgPosterCriticaUser.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    HomeActivity.IDPositionPop = MainActivity.idCriticaUsu;
                    startActivity(new Intent(CriticaActivity.this, MainActivity.class));
                    finish();
                }
            });
        }
        else{
            LocalDate localDate2 = LocalDate.parse(LogActivity.dataAssistido);
            DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("dd MMMM, yyyy", new Locale("pt", "BR"));

            ratingBarCritica.setRating(LogActivity.ratingBar.getRating());
            txtCriticaUser.setText(LogActivity.critica);
            btnDataCritica.setText("Assistido em " + localDate2.format(dateTimeFormatter2));
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
            txtPerfilCritica.setText(HomeActivity.nickusuario);
            Picasso
                    .get()
                    .load(HomeActivity.iconusuario)
                    .into(imgPerfilCritica);
            imgPosterCriticaUser.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    HomeActivity.IDPositionPop = LogActivity.idfilmeassistido;
                    startActivity(new Intent(CriticaActivity.this, MainActivity.class));
                    finish();
                }
            });
        }
    }

    /*public void buscarCritica(String userID, String criticaID){
        Call<ArrayList<ItemCritica>> result = ApiClient.getUserService().getAllFeedbackMovie(filmeID);
        result.enqueue(new Callback<ArrayList<ItemCritica>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemCritica>> call, Response<ArrayList<ItemCritica>> response) {
                if(response.isSuccessful()){
                    criticasfilme = response.body();

                    int b=0;

                    for(b=0; b<itemsfilme.size(); b++){
                        buscarUsuarios(criticasfilme.get(b).getIdCritica(), criticasfilme.get(b).getIdFilme(), criticasfilme.get(b).getIdUsuario(), criticasfilme.get(b).getTextoCritica(), criticasfilme.get(b).notaCritica, criticasfilme.get(b).getDataCritica(), criticasfilme.get(b).getQtdCurtidaCritica());
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemCritica>> call, Throwable t) {
                StyleableToast.makeText(MainActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }*/



}