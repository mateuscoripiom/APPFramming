package com.example.framming;

import static com.example.framming.HomeActivity.items;
import static com.example.framming.HomeActivity.swtPosition;
import static com.example.framming.PesquisaActivity.itemsbusca;
import static com.example.framming.PosterActivity.posterArray;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmesListaActivity extends AppCompatActivity {

    RecyclerView recyclerViewListas;
    TextView txtNomList, txtErroLista;
    Toolbar toolbarlista;
    ImageView imgErro, imgFundoLista;

    public static ArrayList<MovieFinal> itemsfilmefinal = new ArrayList<>();
    public static String IDFilmeFundo;
    public static ArrayList<Root> rootfiles = new ArrayList<>();
    public static ArrayList<Movie> movies = new ArrayList<>();
    public static boolean usoLista = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmes_lista);
        toolbarlista = findViewById(R.id.toolbar4);


        recyclerViewListas = findViewById(R.id.recyclerViewListas);
        txtNomList = findViewById(R.id.txtNomList);
        txtErroLista = findViewById(R.id.txtErroLista);
        imgErro = findViewById(R.id.imageView30);
        imgFundoLista = findViewById(R.id.imgFundo24);


        buscarFListas(ListasActivity.IDLista);

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
                ProfileActivity.usoProfile = false;
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
                itemsfilmefinal.clear();
                rootfiles.clear();
                startActivity(new Intent(FilmesListaActivity.this, ListasActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public void buscarFListas(String IDLista){
        Call<ArrayList<Root>> result = ApiClient.getUserService().getList(HomeActivity.IDUser, IDLista);
        result.enqueue(new Callback<ArrayList<Root>>() {
            @Override
            public void onResponse(Call<ArrayList<Root>> call, Response<ArrayList<Root>> response) {
                if (response.code() != 200) {

                }
                if(response.isSuccessful()){
                    rootfiles = response.body();


                    txtNomList.setText(rootfiles.get(0).getDescricaoLista());
                     Picasso
                             .get()
                             .load("https://www.themoviedb.org/t/p/original" + rootfiles.get(1).getMovies().get(0).getBackdrop_path())
                             .into(imgFundoLista);
                     int b = 0;
                     for (b = 0; b < rootfiles.get(1).getMovies().size(); b++) {
                         buscarPosterFav(rootfiles.get(1).getMovies().get(b).getId(), rootfiles.get(1).getMovies().get(b).getPoster_path(), rootfiles.get(1).getMovies().get(b).getBackdrop_path(), b);
                     }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Root>> call, Throwable t) {
                StyleableToast.makeText(FilmesListaActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void buscarPosterFav(String filmeID, String linkPoster, String backdrop, Integer ordemArray) {
        Call<PosterResponse> result = ApiClient.getUserService().getAllPosters(HomeActivity.IDUser, filmeID);
        result.enqueue(new Callback<PosterResponse>() {
            @Override
            public void onResponse(Call<PosterResponse> call, Response<PosterResponse> response) {
                if (response.code() != 200) {
                    itemsfilmefinal.add(new MovieFinal(filmeID, linkPoster, backdrop, ordemArray));

                }
                if (response.isSuccessful()) {
                    itemsfilmefinal.add(new MovieFinal(filmeID, response.body().getLinkPoster(), backdrop, ordemArray));
                    //Log.v("Tag", "The title" + itemsfavfinal.get(1).getPosterFilme().toString());
                }
                sortListByArrayMovie(itemsfilmefinal);
                recyclerViewListas.setLayoutManager(new GridLayoutManager(FilmesListaActivity.this, 3));
                recyclerViewListas.setAdapter(new MyAdapterFListas(getApplicationContext(), itemsfilmefinal));

                 recyclerViewListas.addOnItemTouchListener(
                         new RecyclerItemClickListener(getApplicationContext(), recyclerViewListas, new RecyclerItemClickListener.OnItemClickListener() {
                             @Override
                             public void onItemClick(View view, int position) {

                                 HomeActivity.IDPositionPop = itemsfilmefinal.get(position).getId();
                                 startActivity(new Intent(FilmesListaActivity.this, MainActivity.class));
                                 itemsfilmefinal.clear();
                                 rootfiles.clear();
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
                StyleableToast.makeText(FilmesListaActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });


    }

    private void sortListByArrayMovie(ArrayList<MovieFinal> theArrayListEvents) {
        Collections.sort(theArrayListEvents, new EventDetailSortByArrayMovie());
    }
}