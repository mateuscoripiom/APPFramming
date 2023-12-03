package com.example.framming;

import static com.example.framming.HomeActivity.items;
import static com.example.framming.HomeActivity.swtPosition;
import static com.example.framming.PesquisaActivity.itemsbusca;
import static com.example.framming.PosterActivity.posterArray;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    public static ArrayList<ItemFeedback> itemsfeedbackrecente = new ArrayList<>();
    public static ArrayList<ItemFeedbackFRecente> itemsffinalrecente = new ArrayList<>();
    public static ArrayList<FilmesResponse> itemsfav = new ArrayList<>();
    public static ArrayList<ItemFavFinal> itemsfavfinal = new ArrayList<>();
    CardView cardPontos, cardDiario, cardQueroVer;
    TextView txtNomeUsuario, txtUserName, txtmsgErro1, txtmsgErro2;
    ImageView imgIconUsuario;
    Button btntipouser;

    Toolbar toolbarprofile;
    RecyclerView recyclerViewRecente, recyclerViewFav;
    ImageView imgPosterFav1, imgPosterFav2, imgPosterFav3, imgPosterFav4, imgFundoFav;
    //public static int ordemArray;

    public static int positionf;
    public static boolean usoProfile = false;
    public static String profileuserID, profilecriticaID;

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
        imgFundoFav = findViewById(R.id.imgFundo7);
        recyclerViewFav = findViewById(R.id.recyclerViewFav);
        txtmsgErro1 = findViewById(R.id.txtMsgCritica2);
        txtmsgErro2 = findViewById(R.id.txtMsgCritica3);


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
                    itemsfav.clear();
                    itemsfavfinal.clear();
                    itemsffinalrecente.clear();
                    startActivity(new Intent(ProfileActivity.this, Favorito1Activity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    finish();
                }

                return false;
            }
        });

        buscarFavoritos();


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

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                DiaryActivity.itemsffinal.clear();
                DiaryActivity.itemsfeedback.clear();
                DiaryActivity.contagemdiario = 0;
                HomeActivity.ID = null;
                HomeActivity.IDPositionPop = null;
                HomeActivity.itemsfinal.clear();
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
                itemsfav.clear();
                itemsffinalrecente.clear();
                itemsfavfinal.clear();
                startActivity(new Intent(ProfileActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

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
                        txtmsgErro2.setVisibility(View.GONE);
                        buscarFilmesC(HomeActivity.IDUser, itemsfeedbackrecente.get(b).getIdFilme(), itemsfeedbackrecente.get(b).getNotaCritica(), itemsfeedbackrecente.get(b).getIdCritica(), itemsfeedbackrecente.get(b).getDataCritica());
                    }

                    if(response.body().toString().equals("[]")){
                        txtmsgErro2.setVisibility(View.VISIBLE);
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

    public void buscarFilmesC(String userID, String idFilmeC, Float notaFilmC, String idCriticaC, String dataCritica){
        Call<ArrayList<FilmesResponse>> result = ApiClient.getUserService().getAllDataFilme(idFilmeC);
        result.enqueue(new Callback<ArrayList<FilmesResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<FilmesResponse>> call, Response<ArrayList<FilmesResponse>> response) {
                if(response.isSuccessful()){
                    itemsffinalrecente.add(new ItemFeedbackFRecente(idFilmeC, idCriticaC, notaFilmC, response.body().get(0).getPoster_path(), dataCritica));
                    sortListByDate(itemsffinalrecente);
                    MyAdapterRecente myAdapterRecente = new MyAdapterRecente(ProfileActivity.this, itemsffinalrecente);
                    LinearLayoutManager manager = new LinearLayoutManager(ProfileActivity.this, recyclerViewRecente.HORIZONTAL, false);
                    recyclerViewRecente.setLayoutManager(manager);
                    recyclerViewRecente.setAdapter(myAdapterRecente);

                    recyclerViewRecente.addOnItemTouchListener(
                            new RecyclerItemClickListener(getApplicationContext(), recyclerViewRecente, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    usoProfile = true;
                                    profileuserID = userID;
                                    profilecriticaID = itemsffinalrecente.get(position).getIdCritica();
                                    startActivity(new Intent(ProfileActivity.this, CriticaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

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
            public void onFailure(Call<ArrayList<FilmesResponse>> call, Throwable t) {
                StyleableToast.makeText(ProfileActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void buscarFavoritos(){
        Call<ArrayList<FilmesResponse>> result = ApiClient.getUserService().getFavoritos(HomeActivity.IDUser);
        result.enqueue(new Callback<ArrayList<FilmesResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<FilmesResponse>> call, Response<ArrayList<FilmesResponse>> response) {
                if(response.isSuccessful()){
                    itemsfav = response.body();

                    int b=0;

                    for(b=0; b<itemsfav.size(); b++){
                        txtmsgErro1.setVisibility(View.GONE);
                        buscarPosterFav(itemsfav.get(b).getId(), itemsfav.get(b).getPoster_path(), itemsfav.get(b).getBackdrop_path(), b);
                    }

                    if(response.body().toString().equals("[]")){
                        txtmsgErro1.setVisibility(View.VISIBLE);
                    }

                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<FilmesResponse>> call, Throwable t) {
                StyleableToast.makeText(ProfileActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void buscarPosterFav(String filmeID, String linkPoster, String backdrop, Integer ordemArray) {
        Call<PosterResponse> result = ApiClient.getUserService().getAllPosters(HomeActivity.IDUser, filmeID);
        result.enqueue(new Callback<PosterResponse>() {
            @Override
            public void onResponse(Call<PosterResponse> call, Response<PosterResponse> response) {
                if (response.code() != 200) {
                    itemsfavfinal.add(new ItemFavFinal(filmeID, linkPoster, backdrop, ordemArray));

                }
                if (response.isSuccessful()) {
                    itemsfavfinal.add(new ItemFavFinal(filmeID, response.body().getLinkPoster(), backdrop, ordemArray));
                    //Log.v("Tag", "The title" + itemsfavfinal.get(1).getPosterFilme().toString());
                }
                sortListByArray(itemsfavfinal);
                MyAdapterFav myAdapterFav = new MyAdapterFav(ProfileActivity.this, itemsfavfinal);
                LinearLayoutManager manager = new LinearLayoutManager(ProfileActivity.this, recyclerViewFav.HORIZONTAL, false);
                recyclerViewFav.setLayoutManager(manager);
                recyclerViewFav.setAdapter(myAdapterFav);

                Picasso
                        .get()
                        .load("https://www.themoviedb.org/t/p/original" + itemsfavfinal.get(0).getBackdrop())
                        .into(imgFundoFav);

                recyclerViewFav.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), recyclerViewFav, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                HomeActivity.IDPositionPop = itemsfavfinal.get(position).getIdFilme();
                                startActivity(new Intent(ProfileActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                            }

                            @Override/*IDPopUp = items.get(position).getIdpop();
                                startActivity(new Intent(HomeActivity.this, PopUpActivity.class));*/
                            public void onLongItemClick(View view, int position) {

                                //Createpopupwindows();
                            }
                        })
                );
            }

            @Override
            public void onFailure(Call<PosterResponse> call, Throwable t) {
                StyleableToast.makeText(ProfileActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    private void sortListByDate(ArrayList<ItemFeedbackFRecente> theArrayListEvents) {
        Collections.sort(theArrayListEvents, new EventDetailSortByDateProfile());
    }
    private void sortListByArray(ArrayList<ItemFavFinal> theArrayListEvents) {
        Collections.sort(theArrayListEvents, new EventDetailSortByArray());
    }

}