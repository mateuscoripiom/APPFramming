package com.example.framming;

import static com.example.framming.HomeActivity.items;
import static com.example.framming.HomeActivity.itemsnac;
import static com.example.framming.HomeActivity.swtPosition;
import static com.example.framming.PesquisaActivity.itemsbusca;
import static com.example.framming.PosterActivity.posterArray;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.cardview.widget.CardView;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public static ImageView imgPoster;
    public static ImageView imgBackground;
    private TextView txtName, txtAno, txtDuracao, txtSinopse, txtNomeOriginal;
    private Button btnGenero, btnPesquisa, btndiario, btnhorarioses, btnDarNota, btnQueroVer;
    public static ArrayList<FeedbackResponse> itemscritica = new ArrayList<FeedbackResponse>();
    public static ArrayList<ItemFilme> itemsfilme = new ArrayList<>();
    public static EditText etxtID;
    private ImageButton imgbtnposter, imgbtnvoltar;
    public static boolean usado = false;
    public static boolean usadoEscolha = false;
    public static boolean b = false;
    public static boolean c = false;
    public static int IDLoader;
    public static String IDFilme;
    public static String linkFilmeSalvo;
    public static String nomeFilmeTipIng, posterFilmeTipIng, fundoFilmeTipIng;
    public static boolean listadoQV = false;

    Toolbar toolbar;
    CardView cardSuaNota;
    RatingBar ratingSuaNota;
    TextView txtnumvisu, txtmedianota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);

        txtnumvisu = findViewById(R.id.txtnumvisu);
        txtmedianota = findViewById(R.id.txtmedianota);
        cardSuaNota = findViewById(R.id.cardSuaNota);
        ratingSuaNota = findViewById(R.id.ratingBarSuaNota);
        imgBackground = findViewById(R.id.imgFundo);
        imgPoster = findViewById(R.id.imgPoster);
        txtName = findViewById(R.id.txtName);
        txtAno = findViewById(R.id.txtAno);
        txtDuracao = findViewById(R.id.txtDuracao);
        txtSinopse = findViewById(R.id.txtSinopse);
        btnGenero = findViewById(R.id.btnGenero);
        imgbtnposter = findViewById(R.id.imgbtnposter);
        imgbtnvoltar = findViewById(R.id.imgbtnvoltar);
        btndiario = findViewById(R.id.btndiario);
        txtNomeOriginal = findViewById(R.id.txtNomeOriginal);
        btnhorarioses = findViewById(R.id.btnhorarioses);
        toolbar = findViewById(R.id.toolbar2);
        btnDarNota = findViewById(R.id.btnDarNota);
        btnQueroVer = findViewById(R.id.btnQueroVer);

        toolbar.inflateMenu(R.menu.movie_menu);


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.nav_altposter) {
                    startActivity(new Intent(MainActivity.this, PosterActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    PosterActivity.IDPosition = null;
                    MainActivity.linkFilmeSalvo = null;
                    finish();
                } else if (item.getItemId() == R.id.nav_addlista) {
                    // do something
                }

                return false;
            }
        });

        if(HomeActivity.ID != null) {
            IDFilme = HomeActivity.ID;
        }
        if(HomeActivity.IDPositionPop != null){
            IDFilme = HomeActivity.IDPositionPop;
        }
        if(HomeActivity.IDPositionPop != null){
            IDFilme = HomeActivity.IDPositionPop;
        }
        if(PesquisaActivity.IDpesquisa != null){
            IDFilme = PesquisaActivity.IDpesquisa;
        }

        buscarFilmeAPI(IDFilme);


        buscaPosterSalvo();
        buscarCriticaF(IDFilme);
        buscaQueroV(IDFilme);

        btnQueroVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveQueroVer(createRequestQ());
            }
        });


        btnDarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NotaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

        btnhorarioses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, tipoingressoActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

        btndiario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LogActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        });

        imgbtnvoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity.ID = null;
                HomeActivity.IDPositionPop = null;
                PosterActivity.IDPosition = null;
                MainActivity.linkFilmeSalvo = null;
                linkFilmeSalvo = null;
                usado = false;
                usadoEscolha = false;
                listadoQV = false;
                QueroVerActivity.contagemquerover = 0;
                PesquisaActivity.IDpesquisa = null;
                HomeActivity.usadobtn = 0;
                posterArray.clear();
                itemsbusca.clear();
                items.clear();
                swtPosition = false;
                startActivity(new Intent(MainActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                HomeActivity.ID = null;
                HomeActivity.IDPositionPop = null;
                PosterActivity.IDPosition = null;
                usado = false;
                usadoEscolha = false;
                QueroVerActivity.contagemquerover = 0;
                linkFilmeSalvo = null;
                listadoQV = false;
                PesquisaActivity.IDpesquisa = null;
                HomeActivity.usadobtn = 0;
                posterArray.clear();
                itemsbusca.clear();
                MainActivity.linkFilmeSalvo = null;
                items.clear();
                swtPosition = false;
                startActivity(new Intent(MainActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }



    public void buscaPosterSalvo(){
        String movieString = null;
        if(HomeActivity.ID != null) {
            movieString = HomeActivity.ID;
            IDFilme = movieString;
        }
        if(HomeActivity.IDPositionPop != null){
            movieString = HomeActivity.IDPositionPop;
            IDFilme = movieString;
        }
        if(HomeActivity.IDPositionPop != null){
            movieString = HomeActivity.IDPositionPop;
            IDFilme = movieString;
        }
        if(PesquisaActivity.IDpesquisa != null){
            movieString = PesquisaActivity.IDpesquisa;
            IDFilme = movieString;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://framming-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);

        Call<PosterResponse> call = userService.getAllPosters(HomeActivity.IDUser, IDFilme);

        call.enqueue(new Callback<PosterResponse>() {
            @Override
            public void onResponse(Call<PosterResponse> call, Response<PosterResponse> response) {
                if(response.code() != 200){
                    //Toast.makeText(MainActivity.this, "Cheque sua conexão", Toast.LENGTH_SHORT).show();
                    buscaInfoFilme();
                }
                if(response.isSuccessful()) {
                    String content = "";

                    content = response.body().getLinkPoster();

                    if (content != null || content != "") {
                        usadoEscolha = true;
                        linkFilmeSalvo = content;
                    }

                    //Toast.makeText(MainActivity.this, content, Toast.LENGTH_SHORT).show();

                    buscaInfoFilme();
                }
            }

            @Override
            public void onFailure(Call<PosterResponse> call, Throwable t) {
                buscaInfoFilme();
            }
        });
    }



    public void buscaInfoFilme() {
        // Recupera a string de busca.
        String movieString = null;
        if(HomeActivity.ID != null) {
            movieString = HomeActivity.ID;
            IDFilme = movieString;
        }
        if(HomeActivity.IDPositionPop != null){
            movieString = HomeActivity.IDPositionPop;
            IDFilme = movieString;
        }
        if(HomeActivity.IDPositionPop != null){
            movieString = HomeActivity.IDPositionPop;
            IDFilme = movieString;
        }
        if(PesquisaActivity.IDpesquisa != null){
            movieString = PesquisaActivity.IDpesquisa;
            IDFilme = movieString;
        }

        // Verifica o status da conexão de rede
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        /* Se a rede estiver disponivel e o campo de busca não estiver vazio
         iniciar o Loader CarregaLivros */
        if (networkInfo != null && networkInfo.isConnected()
                && movieString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("movieString", movieString);
            getSupportLoaderManager().restartLoader(1, queryBundle, this);
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            if (movieString.length() == 0) {
                Toast.makeText(MainActivity.this, "Termo inválido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String movieString = "";
        if (args != null) {
            movieString = args.getString("movieString");
        }

        return new CarregaFilmeIDFramming(this, movieString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
            try {
                // Converte a resposta em Json
                JSONObject jsonObject = new JSONObject(data);
                // Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
                // Obtem o JSONArray dos itens de livros
                // JSONArray itemsArray = jsonObject.getJSONArray("genres");
                // Toast.makeText(this, itemsArray.toString(), Toast.LENGTH_SHORT).show();
                // inicializa o contador
                int i = 0;
                String nome = null;
                String imgfundo = null;
                String imgposter = null;
                String sinopse = null;
                String duracao = null;
                String ano = null;
                String nomeoriginal = null;
                // Procura pro resultados nos itens do array
                while (i < jsonObject.length() &&
                        (nome == null) && (imgfundo == null) && (nomeoriginal == null) && (imgposter == null) && (sinopse == null) && (duracao == null) && (ano == null)) {
                    // Obtem a informação
                    Object title = jsonObject.get("title"); // pega o title no object json
                    Object backdrop = jsonObject.get("backdrop_path");
                    Object poster = jsonObject.get("poster_path");
                    Object overview = jsonObject.get("overview");
                    Object originalname = jsonObject.get("original_title");
                    Object runtime = jsonObject.get("runtime");
                    Object year = jsonObject.get("release_date");

                    // Toast.makeText(this, "MOVIE:" + title, Toast.LENGTH_SHORT).show();
                    //  Obter autor e titulo para o item,
                    // erro se o campo estiver vazio
                    try {
                        nome = title.toString();
                        imgfundo = backdrop.toString();
                        imgposter = poster.toString();
                        sinopse = overview.toString();
                        duracao = runtime.toString();
                        nomeoriginal = originalname.toString();
                        ano = year.toString();
                        // Toast.makeText(this, "NOME:" + nome, Toast.LENGTH_SHORT).show();
                    } catch (Exception err) {
                        err.printStackTrace();
                    }
                    // move para a proxima linha
                    i++;
                }

                JSONArray itemsArray = jsonObject.getJSONArray("genres");
                int z = 0;
                String ngenero = null;
                while (z < itemsArray.length() &&
                        (ngenero == null)) {
                    // Obtem a informação
                    JSONObject genre = itemsArray.getJSONObject(z);
                    // JSONObject volumeInfo = genre.getJSONObject("name");
                    //Toast.makeText(this, genre.toString(), Toast.LENGTH_SHORT).show();
                    //  Obter autor e titulo para o item,
                    // erro se o campo estiver vazio
                    try {
                        ngenero = genre.getString("name");
                        //Toast.makeText(this, ngenero.toString(), Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    // move para a proxima linha
                    z++;
                }
                //mostra o resultado qdo possivel.
                if ((nome != null) && (imgfundo != null) && (nomeoriginal != null) && (imgposter != null) && (sinopse != null) && (duracao != null) && (ano != null) && (ngenero != null)) {
                    txtName.setText(nome);
                    nomeFilmeTipIng = nome;
                    txtDuracao.setText(duracao + "min");
                    txtAno.setText(ano);
                    txtNomeOriginal.setText(nomeoriginal);
                    btnGenero.setText(ngenero);
                    if (sinopse == "") {
                        txtSinopse.setText(buscaInfoFilmeEN.filmesinen);
                    } else {
                        txtSinopse.setText(sinopse);
                    }
                    fundoFilmeTipIng = imgfundo;
                    Picasso
                            .get()
                            .load("https://www.themoviedb.org/t/p/original" + imgfundo)
                            .into(imgBackground);

                    if (usadoEscolha == true) {
                        Picasso
                                .get()
                                .load("https://www.themoviedb.org/t/p/original" + linkFilmeSalvo)
                                .into(imgPoster);
                        posterFilmeTipIng = linkFilmeSalvo;
                    } else {
                        Picasso
                                .get()
                                .load("https://www.themoviedb.org/t/p/original" + imgposter)
                                .into(imgPoster);
                        posterFilmeTipIng = imgposter;
                    }


                } else {
                    Toast.makeText(MainActivity.this, "Sem retorno de dados", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                //Toast.makeText(MainActivity.this, "Sem retorno de dados", Toast.LENGTH_SHORT).show();
            }
        //}
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }

    public void buscarCriticaF(String filmeID){

        Call<ArrayList<FeedbackResponse>> result2 = ApiClient.getUserService().getFeedbackMovie(HomeActivity.IDUser, filmeID);
        result2.enqueue(new Callback<ArrayList<FeedbackResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<FeedbackResponse>> call2, Response<ArrayList<FeedbackResponse>> response2) {
                if(response2.isSuccessful()){
                    btnDarNota.setVisibility(View.GONE);
                    cardSuaNota.setVisibility(View.VISIBLE);
                    itemscritica = response2.body();

                    int b=0;

                    for(b=0; b<itemscritica.size(); b++){
                        ratingSuaNota.setRating(itemscritica.get(0).getNotaCritica());
                    }
                    if(response2.body().toString().equals("[]")){
                        btnDarNota.setVisibility(View.VISIBLE);
                        cardSuaNota.setVisibility(View.GONE);
                    }

                }
                else{
                    btnDarNota.setVisibility(View.VISIBLE);
                    cardSuaNota.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FeedbackResponse>> call2, Throwable t) {
                StyleableToast.makeText(MainActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public QueroVerRequest createRequestQ(){
        QueroVerRequest queroVerRequest = new QueroVerRequest();
        queroVerRequest.setIdMovie(IDFilme);

        return queroVerRequest;
    }

    public void saveQueroVer(QueroVerRequest queroVerRequest){
        Call<ResponseBody> result = ApiClient.getUserService().saveQueroVer(HomeActivity.IDUser, queroVerRequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    if(listadoQV == false) {
                        StyleableToast.makeText(MainActivity.this, nomeFilmeTipIng + " marcado como Quero Ver!", Toast.LENGTH_LONG, R.style.exampleToast).show();
                        btnQueroVer.setBackgroundColor(Color.WHITE);
                        btnQueroVer.setTextColor(Color.BLACK);
                        btnQueroVer.setText("REMOVER QUERO VER");
                        listadoQV = true;
                    }
                    else{
                        listadoQV = false;
                        btnQueroVer.setBackgroundColor(Color.TRANSPARENT);
                        btnQueroVer.setTextColor(Color.WHITE);
                        btnQueroVer.setText("QUERO VER");
                        StyleableToast.makeText(MainActivity.this, nomeFilmeTipIng + " removido do Quero Ver!", Toast.LENGTH_LONG, R.style.exampleToast).show();

                    }

                }
                else{
                    Toast.makeText(MainActivity.this, "Salvamento falhou", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Salvamento falhou" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buscaQueroV(String filmeID){

        Call<QueroVerResponse> result = ApiClient.getUserService().getMovieQV(HomeActivity.IDUser, filmeID);
        result.enqueue(new Callback<QueroVerResponse>() {
            @Override
            public void onResponse(Call<QueroVerResponse> call, Response<QueroVerResponse> response) {
                if(response.isSuccessful()){
                    btnQueroVer.setBackgroundColor(Color.WHITE);
                    btnQueroVer.setTextColor(Color.BLACK);
                    btnQueroVer.setText("REMOVER QUERO VER");
                    listadoQV = true;
                    if(response.body().toString().equals("[]")){
                        btnQueroVer.setBackgroundColor(Color.TRANSPARENT);
                        btnQueroVer.setTextColor(Color.WHITE);
                        btnQueroVer.setText("QUERO VER");
                        listadoQV = false;
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<QueroVerResponse> call2, Throwable t) {
                StyleableToast.makeText(MainActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void buscarFilmeAPI(String filmeID){
        Call<ArrayList<ItemFilme>> result = ApiClient.getUserService().getAllDataFilmeAPI(filmeID);
        result.enqueue(new Callback<ArrayList<ItemFilme>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemFilme>> call, Response<ArrayList<ItemFilme>> response) {
                if(response.isSuccessful()){
                    itemsfilme = response.body();

                    int b=0;

                    for(b=0; b<itemsfilme.size(); b++){
                        txtName.setText(itemsfilme.get(0).getTitle());
                        nomeFilmeTipIng = itemsfilme.get(0).getTitle();
                        txtDuracao.setText(itemsfilme.get(0).getRuntime() + "min");
                        txtAno.setText(itemsfilme.get(0).getRelease_date());
                        txtNomeOriginal.setText(itemsfilme.get(0).getOriginal_title());
                        btnGenero.setText(itemsfilme.get(0).getGenre().get(0).getName());
                        txtSinopse.setText(itemsfilme.get(0).getOverview());
                        fundoFilmeTipIng = itemsfilme.get(0).getBackdrop_path();
                        txtnumvisu.setText(itemsfilme.get(0).getQtdVisualizacaoFilme());
                        txtmedianota.setText(itemsfilme.get(0).getNotaFilme());

                        Picasso
                                .get()
                                .load("https://www.themoviedb.org/t/p/original" + itemsfilme.get(0).getBackdrop_path())
                                .into(imgBackground);

                        if (usadoEscolha == true) {
                            Picasso
                                    .get()
                                    .load("https://www.themoviedb.org/t/p/original" + linkFilmeSalvo)
                                    .into(imgPoster);
                            posterFilmeTipIng = linkFilmeSalvo;
                        } else {
                            Picasso
                                    .get()
                                    .load("https://www.themoviedb.org/t/p/original" + itemsfilme.get(0).getPoster_path())
                                    .into(imgPoster);
                            posterFilmeTipIng = itemsfilme.get(0).getPoster_path();
                        }
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemFilme>> call, Throwable t) {
                StyleableToast.makeText(MainActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }



}