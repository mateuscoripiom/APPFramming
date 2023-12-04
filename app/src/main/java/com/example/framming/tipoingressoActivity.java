package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class tipoingressoActivity extends AppCompatActivity {

    Integer numIng = 0;
    ImageView btnmenos, btnmais, imgPosterTipoIng;
    TextView txtnumIng, txtNomeFilmeTipIng, txtNumSala, txtDetSessao, txtNomeCinema;
    RecyclerView recyclerViewTipIng;
    Button btnComprar;

    public static ArrayList<ItemFilme> itemsfilme = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_tipoingresso);

        btnmenos = findViewById(R.id.imageView17);
        btnmais = findViewById(R.id.imageView19);
        imgPosterTipoIng = findViewById(R.id.imgPosterTipoIng);
        txtNomeFilmeTipIng = findViewById(R.id.txtNomeFilmeTipIng);
        txtNumSala = findViewById(R.id.txtNumSala);
        txtDetSessao = findViewById(R.id.txtDetSessao);
        recyclerViewTipIng = findViewById(R.id.recyclerViewTipIng);
        txtNomeCinema = findViewById(R.id.txtNomeCinema);
        btnComprar = findViewById(R.id.btnComprar);

        buscarFilmeAPI();
        buscarCinema();

        txtNumSala.setText("Sala: " + MainActivity.salatiping);
        txtDetSessao.setText(MainActivity.datsessaotiping + " - " + MainActivity.horsessaotiping);


       /* MyAdapterTipIng myAdapterTipIng = new MyAdapterTipIng(tipoingressoActivity.this, MainActivity.tipingg);
        LinearLayoutManager manager = new LinearLayoutManager(tipoingressoActivity.this, recyclerViewTipIng.VERTICAL, false);
        recyclerViewTipIng.setLayoutManager(manager);
        recyclerViewTipIng.setAdapter(myAdapterTipIng);*/


        txtnumIng = findViewById(R.id.textView30);

        btnmenos.setAlpha(40);

        btnComprar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(tipoingressoActivity.this, formadepagamentoActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

            }
        });

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

    public void buscarFilmeAPI(){
        Call<ArrayList<ItemFilme>> result = ApiClient.getUserService().getAllDataFilmeAPI(MainActivity.idfilmetiping);
        result.enqueue(new Callback<ArrayList<ItemFilme>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemFilme>> call, Response<ArrayList<ItemFilme>> response) {
                if(response.isSuccessful()){
                    itemsfilme = response.body();

                    int b=0;

                    for(b=0; b<itemsfilme.size(); b++){
                       Picasso
                                    .get()
                                    .load("https://www.themoviedb.org/t/p/original" + itemsfilme.get(0).getPoster_path())
                                    .into(imgPosterTipoIng);
                        txtNomeFilmeTipIng.setText(MainActivity.itemsfilme.get(0).getTitle());
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemFilme>> call, Throwable t) {
                StyleableToast.makeText(tipoingressoActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void buscarCinema(){
        Call<CinemaResponse> result = ApiClient.getUserService().getCinemaData(MainActivity.tokencintiping);
        result.enqueue(new Callback<CinemaResponse>() {
            @Override
            public void onResponse(Call<CinemaResponse> call, Response<CinemaResponse> response) {
                if(response.isSuccessful()){
                    txtNomeCinema.setText(response.body().getNomeCinema());
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<CinemaResponse> call, Throwable t) {
                StyleableToast.makeText(tipoingressoActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }


}