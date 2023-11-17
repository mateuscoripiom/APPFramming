package com.example.framming;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiaryActivity extends AppCompatActivity {

    public static ArrayList<ItemFeedback> itemsfeedback = new ArrayList<>();
    public static ArrayList<ItemFeedbackF> itemsffinal = new ArrayList<>();
    public static int contagemdiario;

    RecyclerView recyclerViewDiary;
    TextView contagemtotal, txtnomeusudiario;
    ImageView imgperfildiario;
    public static boolean diarioCritica = false;
    public static float notaCritica;
    public static String nomefilmeCritica, imgfundoCritica, imgposterCritica, dataCritica, textoCritica, anoCritica, fundoCritica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        recyclerViewDiary = findViewById(R.id.recyclerviewcritica);
        buscarCriticas();
        contagemtotal = findViewById(R.id.contagemtotal);

        txtnomeusudiario = findViewById(R.id.txtnomeusudiario);
        imgperfildiario = findViewById(R.id.imgperfildiario);

        txtnomeusudiario.setText("@" + HomeActivity.nickusuario);
        Picasso
                .get()
                .load(HomeActivity.iconusuario)
                .into(imgperfildiario);



        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                itemsffinal.clear();
                itemsfeedback.clear();
                contagemdiario = 0;
                startActivity(new Intent(DiaryActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }



    public void buscarCriticas(){
        Call<ArrayList<ItemFeedback>> result = ApiClient.getUserService().getAllFeedback(HomeActivity.IDUser);
        result.enqueue(new Callback<ArrayList<ItemFeedback>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemFeedback>> call, Response<ArrayList<ItemFeedback>> response) {
                if(response.isSuccessful()){
                    int position = 0;
                    itemsfeedback = response.body();

                    int b=0;

                    for(b=0; b<itemsfeedback.size(); b++){
                        contagemdiario++;
                        contagemtotal.setText("Você já marcou " + contagemdiario + " filmes no seu diário");
                        buscarFilmesC(itemsfeedback.get(b).getIdFilme(), itemsfeedback.get(b).getNotaCritica(), itemsfeedback.get(b).getDataCritica(), itemsfeedback.get(b).getIdCritica(),itemsfeedback.get(b).getFeedbackCritica());
                    }

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemFeedback>> call, Throwable t) {
                StyleableToast.makeText(DiaryActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();

            }
        });
    }

    public void buscarFilmesC(String idFilmeC, Float notaFilmC, String dataFilmeC, String idCriticaC, String feedbackCritica){
        Call<FilmesResponse> result = ApiClient.getUserService().getAllDataFilme(idFilmeC);
        result.enqueue(new Callback<FilmesResponse>() {
            @Override
            public void onResponse(Call<FilmesResponse> call, Response<FilmesResponse> response) {
                if(response.isSuccessful()){
                    nomefilmeCritica = response.body().getTitle();
                    anoCritica = response.body().getRelease_date();
                    imgfundoCritica = response.body().getBackdrop_path();
                    imgposterCritica = response.body().getPoster_path();
                    notaCritica = notaFilmC;
                    dataCritica = dataFilmeC;
                    textoCritica = feedbackCritica;


                    itemsffinal.add(new ItemFeedbackF(idFilmeC, idCriticaC, notaFilmC, dataFilmeC, response.body().getPoster_path(), response.body().getBackdrop_path(), response.body().getTitle(), response.body().getRelease_date(), response.body().getOriginal_title()));
                    MyAdapterDiary myAdapterDiary = new MyAdapterDiary(DiaryActivity.this, itemsffinal);
                    LinearLayoutManager manager = new LinearLayoutManager(DiaryActivity.this, recyclerViewDiary.VERTICAL, false);
                    recyclerViewDiary.setLayoutManager(manager);
                    recyclerViewDiary.setAdapter(myAdapterDiary);

                    recyclerViewDiary.addOnItemTouchListener(
                            new RecyclerItemClickListener(getApplicationContext(), recyclerViewDiary, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {

                                    diarioCritica = true;
                                    startActivity(new Intent(DiaryActivity.this, CriticaActivity.class));
                                }

                                @Override/*IDPopUp = items.get(position).getIdpop();
                                startActivity(new Intent(HomeActivity.this, PopUpActivity.class));*/
                                public void onLongItemClick(View view, int position) {

                                    //Createpopupwindows();
                                }
                            })
                    );
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<FilmesResponse> call, Throwable t) {
                StyleableToast.makeText(DiaryActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }
}