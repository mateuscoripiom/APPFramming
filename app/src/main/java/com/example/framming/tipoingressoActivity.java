package com.example.framming;

import static com.example.framming.HomeActivity.items;
import static com.example.framming.HomeActivity.itemsfinal;
import static com.example.framming.HomeActivity.swtPosition;
import static com.example.framming.PesquisaActivity.itemsbusca;
import static com.example.framming.PosterActivity.posterArray;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
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
    TextView txtMsgCritica4;

    public static ArrayList<ItemFilme> itemsfilme = new ArrayList<>();
    public static ArrayList<ItemSessionTipIng> itemssession = new ArrayList<>();

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
        txtMsgCritica4 = findViewById(R.id.txtMsgCritica4);

        buscarFilmeAPI();
        buscarCinema();
        buscarSessaoID();

        txtNumSala.setText("Sala: " + MainActivity.salatiping);
        txtDetSessao.setText(MainActivity.datsessaotiping + " - " + MainActivity.horsessaotiping);





        txtnumIng = findViewById(R.id.textView30);

        btnmenos.setAlpha(40);

        btnComprar.setText("PROSSEGUIR");

        btnComprar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //startActivity(new Intent(tipoingressoActivity.this, formadepagamentoActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                Log.v("Preço ", String.valueOf(MyAdapterTipIng.valorFIng));
                Log.v("TIp: ", String.valueOf(MyAdapterTipIng.numIngr));
                if(MyAdapterTipIng.valorFIng == 0){
                    StyleableToast.makeText(tipoingressoActivity.this, "Por favor, selecione pelo menos 1 ingresso!", Toast.LENGTH_LONG, R.style.alertToast).show();
                }
                else{
                    startActivity(new Intent(tipoingressoActivity.this, formadepagamentoActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                }
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
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                MainActivity.sessionsfinalf.clear();
                MainActivity.sessionsdata.clear();
                MainActivity.sessionsfinal.clear();
                startActivity(new Intent(tipoingressoActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
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

    public void buscarSessaoID(){
        Call<ArrayList<ItemSessionTipIng>> result = ApiClient.getUserService().getSessionID(MainActivity.idsessaotiping);
        result.enqueue(new Callback<ArrayList<ItemSessionTipIng>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemSessionTipIng>> call, Response<ArrayList<ItemSessionTipIng>> response) {
                if(response.isSuccessful()){
                    itemssession = response.body();

                    int b=0;
                    if(itemssession.get(0).getIngressos().toString().equals("[]")){
                        txtMsgCritica4.setVisibility(View.VISIBLE);
                        txtMsgCritica4.setText("Parece que esta sessão não\n possui ingressos a venda");
                        btnComprar.setVisibility(View.GONE);
                    }
                    else if(itemssession.get(0).getIngressos().get(0).toString().equals("Ingressos foram esgotados!")){
                        txtMsgCritica4.setVisibility(View.VISIBLE);
                        txtMsgCritica4.setText("Ingressos foram esgotados!");
                        btnComprar.setVisibility(View.GONE);
                    }
                    else {


                        for (b = 0; b < itemsfilme.size(); b++) {
                            MyAdapterTipIng myAdapterTipIng = new MyAdapterTipIng(tipoingressoActivity.this, itemssession.get(b).getIngressos());
                            LinearLayoutManager manager = new LinearLayoutManager(tipoingressoActivity.this, recyclerViewTipIng.VERTICAL, false);
                            recyclerViewTipIng.setLayoutManager(manager);
                            recyclerViewTipIng.setAdapter(myAdapterTipIng);
                        }
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemSessionTipIng>> call, Throwable t) {
                StyleableToast.makeText(tipoingressoActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }


}