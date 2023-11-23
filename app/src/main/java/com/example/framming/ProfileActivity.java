package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    public static ArrayList<ItemFeedback> itemsfeedbackrecente = new ArrayList<>();
    public static ArrayList<ItemFeedbackFRecente> itemsffinalrecente = new ArrayList<>();
    CardView cardPontos, cardDiario, cardQueroVer;
    TextView txtNomeUsuario, txtUserName;
    ImageView imgIconUsuario;
    Button btntipouser;

    Toolbar toolbarprofile;
    RecyclerView recyclerViewRecente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        cardPontos = findViewById(R.id.cardPontos);
        txtNomeUsuario = findViewById(R.id.txtNomeUsuario);
        txtUserName = findViewById(R.id.txtUserName);
        imgIconUsuario = findViewById(R.id.imgIconUsuario);
        btntipouser = findViewById(R.id.btntipouser);
        cardDiario = findViewById(R.id.cardDiario);
        toolbarprofile = findViewById(R.id.toolbar3);
        recyclerViewRecente = findViewById(R.id.recyclerViewRecente);
        cardQueroVer = findViewById(R.id.cardQueroVer);

        txtNomeUsuario.setText(HomeActivity.nomeusuario);
        txtUserName.setText("@" + HomeActivity.nickusuario);

        toolbarprofile.inflateMenu(R.menu.profile_menu);
        buscarCriticasRecente();
        toolbarprofile.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.nav_editarusu) {
                    startActivity(new Intent(ProfileActivity.this, PosterActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                    finish();
                } else if (item.getItemId() == R.id.nav_editarfav) {

                }

                return false;
            }
        });

        if(HomeActivity.iconusuario.equals("https://imageupload.io/ib/yzauelSzIISZpoC_1697494770.png")){
            Picasso
                    .get()
                    .load(HomeActivity.iconusuario)
                    .into(imgIconUsuario);
        }
        else{
            imgIconUsuario.setImageURI(Uri.parse(HomeActivity.iconusuario));
        }



        if(HomeActivity.tipoperfil.equals("nor")){
            btntipouser.setText("USUÁRIO PIPOCA");
            btntipouser.setBackgroundColor(Color.GRAY);
            btntipouser.setTextColor(Color.WHITE);
        }

        cardPontos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ProfileActivity.this, RecompensasActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

        cardQueroVer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ProfileActivity.this, QueroVerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

        cardDiario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ProfileActivity.this, DiaryActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

    }

    public void buscarCriticasRecente(){
        Call<ArrayList<ItemFeedback>> result = ApiClient.getUserService().getAllFeedback(HomeActivity.IDUser);
        result.enqueue(new Callback<ArrayList<ItemFeedback>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemFeedback>> call, Response<ArrayList<ItemFeedback>> response) {
                if(response.isSuccessful()){
                    int position = 0;
                    itemsfeedbackrecente = response.body();

                    int b=0;

                    for(b=0; b<itemsfeedbackrecente.size(); b++){
                        buscarFilmesC(itemsfeedbackrecente.get(b).getIdFilme(), itemsfeedbackrecente.get(b).getNotaCritica(), itemsfeedbackrecente.get(b).getIdCritica());
                    }

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemFeedback>> call, Throwable t) {
                StyleableToast.makeText(ProfileActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();

            }
        });
    }

    public void buscarFilmesC(String idFilmeC, Float notaFilmC, String idCriticaC){
        Call<FilmesResponse> result = ApiClient.getUserService().getAllDataFilme(idFilmeC);
        result.enqueue(new Callback<FilmesResponse>() {
            @Override
            public void onResponse(Call<FilmesResponse> call, Response<FilmesResponse> response) {
                if(response.isSuccessful()){
                    itemsffinalrecente.add(new ItemFeedbackFRecente(idFilmeC, idCriticaC, notaFilmC, response.body().getPoster_path()));
                    MyAdapterRecente myAdapterRecente = new MyAdapterRecente(ProfileActivity.this, itemsffinalrecente);
                    LinearLayoutManager manager = new LinearLayoutManager(ProfileActivity.this, recyclerViewRecente.HORIZONTAL, false);
                    recyclerViewRecente.setLayoutManager(manager);
                    recyclerViewRecente.setAdapter(myAdapterRecente);

                    recyclerViewRecente.addOnItemTouchListener(
                            new RecyclerItemClickListener(getApplicationContext(), recyclerViewRecente, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {

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
                StyleableToast.makeText(ProfileActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }
}