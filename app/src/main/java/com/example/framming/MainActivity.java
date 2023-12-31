package com.example.framming;

import static com.example.framming.HomeActivity.items;
import static com.example.framming.HomeActivity.itemsfinal;
import static com.example.framming.HomeActivity.itemsnac;
import static com.example.framming.HomeActivity.swtPosition;
import static com.example.framming.HomeActivity.usadobtn;
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
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public static ArrayList<ItemCritica> criticasfilme = new ArrayList<>();
    public static ArrayList<ItemCriticaFinal> criticasfilmefinal = new ArrayList<>();

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
    public static String nomefilmeUsu, dataCriticaUsu, dataFilmeUsu, idCriticaUsu, idFilmeUsu, idUserUsu, textoCriticaUsu, posterFilmeUsu, fundoFilmeUsu, nickUsu, imgPerfilUsu;
    public static float notaCriticaUsu;
    public static boolean usoMainUsu = false;
    public static ArrayList<ItemSession> sessionsdata = new ArrayList<>();
    public static ArrayList<ItemSessionRV> sessionsfinal = new ArrayList<>();
    public static ArrayList<ItemSessionRVF> sessionsfinalf = new ArrayList<>();

    public static ArrayList<ItemSessionRVF> nomefinalcinema = new ArrayList<>();
    public static String nomecinema;
    public static boolean posicaoesconder = false;
    public static ArrayList<ItemSession> datasiguais = new ArrayList<>();

    public static String idfilmetiping, idsessaotiping, tokencintiping, datsessaotiping, horsessaotiping, salatiping;
    public static ArrayList<TipoIng> tipingg;

    Toolbar toolbar;
    CardView cardSuaNota;
    RatingBar ratingSuaNota;
    TextView txtnumvisu, txtmedianota, txtMsgCritica;
    RecyclerView recyclerViewMain, recyclerViewSessoes;
    Button btnSituFilme;

    ArrayList<ParentModelClass> parentModelClassArrayList = new ArrayList<>();
    ArrayList<ChildModelClass> childModelClassArrayList = new ArrayList<>();
    ArrayList<ChildModelClass2> horariosfinais = new ArrayList<>();
    ArrayList<String> nomescinemas = new ArrayList<>();
    public static boolean addLista = false;


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
        recyclerViewSessoes = findViewById(R.id.recyclerViewSessoes);
        txtDuracao = findViewById(R.id.txtDuracao);
        txtSinopse = findViewById(R.id.txtSinopse);
        btnSituFilme = findViewById(R.id.btnSituFilme);
        btnGenero = findViewById(R.id.btnGenero);
        recyclerViewMain = findViewById(R.id.recyclerViewMain);
        imgbtnposter = findViewById(R.id.imgbtnposter);
        imgbtnvoltar = findViewById(R.id.imgbtnvoltar);
        btndiario = findViewById(R.id.btndiario);
        txtNomeOriginal = findViewById(R.id.txtNomeOriginal);
        toolbar = findViewById(R.id.toolbar2);
        btnDarNota = findViewById(R.id.btnDarNota);
        btnQueroVer = findViewById(R.id.btnQueroVer);
        txtMsgCritica = findViewById(R.id.txtMsgCritica);

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
                    addLista = true;
                    startActivity(new Intent(MainActivity.this, ListasActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

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
        txtMsgCritica.setVisibility(View.GONE);


        buscaPosterSalvo();
        buscarCriticaF(IDFilme);
        buscaQueroV(IDFilme);

        buscarSessoes(IDFilme);


        Switch swtmain = findViewById(R.id.swthome2);
        swtmain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swtPosition = true;
                    txtMsgCritica.setVisibility(View.GONE);
                    buscarCriticas(IDFilme);
                    sessionsdata.clear();
                    sessionsfinal.clear();
                    sessionsfinalf.clear();

                } else {
                    swtPosition = false;
                    txtMsgCritica.setVisibility(View.GONE);
                    criticasfilmefinal.clear();
                    criticasfilme.clear();
                    buscarSessoes(IDFilme);


                }
            }
        });

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
                sessionsdata.clear();
                datasiguais.clear();
                sessionsfinal.clear();
                sessionsfinalf.clear();
                usadoEscolha = false;
                listadoQV = false;
                QueroVerActivity.contagemquerover = 0;
                PesquisaActivity.IDpesquisa = null;
                HomeActivity.usadobtn = 0;
                posterArray.clear();
                itemsbusca.clear();
                items.clear();
                itemsfinal.clear();
                swtPosition = false;
                usoMainUsu = false;
                addLista = false;
                criticasfilme.clear();
                QueroVerActivity.itemsquerover.clear();
                QueroVerActivity.itemsqueroverfinal.clear();
                criticasfilmefinal.clear();
                ProfileActivity.itemsfav.clear();
                ProfileActivity.itemsfavfinal.clear();
                ProfileActivity.itemsffinalrecente.clear();
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
                addLista = false;
                listadoQV = false;
                PesquisaActivity.IDpesquisa = null;
                HomeActivity.usadobtn = 0;
                posterArray.clear();
                itemsbusca.clear();
                itemsfinal.clear();
                sessionsdata.clear();
                sessionsfinal.clear();
                sessionsfinalf.clear();
                QueroVerActivity.itemsquerover.clear();
                QueroVerActivity.itemsqueroverfinal.clear();
                MainActivity.linkFilmeSalvo = null;
                items.clear();
                swtPosition = false;
                HomeActivity.itemsfinal.clear();
                usoMainUsu = false;
                criticasfilme.clear();
                criticasfilmefinal.clear();
                ProfileActivity.itemsfav.clear();
                ProfileActivity.itemsfavfinal.clear();
                ProfileActivity.itemsffinalrecente.clear();
                if(QueroVerActivity.usadoqv == true){
                    startActivity(new Intent(MainActivity.this, QueroVerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                }
                else {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                }
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

    public void buscarCriticas(String filmeID){
        Call<ArrayList<ItemCritica>> result = ApiClient.getUserService().getAllFeedbackMovie(filmeID);
        result.enqueue(new Callback<ArrayList<ItemCritica>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemCritica>> call, Response<ArrayList<ItemCritica>> response) {
                if(response.isSuccessful()){
                    criticasfilme = response.body();

                    if(criticasfilme.toString().equals("[]")){
                        txtMsgCritica.setVisibility(View.VISIBLE);
                        txtMsgCritica.setText(R.string.msgfilmecritica);
                    }

                    int b=0;

                    for(b=0; b<criticasfilme.size(); b++){
                        buscarUsuarios(criticasfilme.get(b).getIdCritica(), criticasfilme.get(b).getIdFilme(), criticasfilme.get(b).getIdUsuario(), criticasfilme.get(b).getTextoCritica(), criticasfilme.get(b).notaCritica, criticasfilme.get(b).getDataCritica(), criticasfilme.get(b).getQtdCurtidaCritica());
                    }


                }
                else{
                    txtMsgCritica.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemCritica>> call, Throwable t) {
                StyleableToast.makeText(MainActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void buscarUsuarios(String idCriticaP, String idFilmeP, String idUsuarioP, String textoCriticaP, float notaCriticaP, String dataCriticaP, String qtdCurtidaCritica){
        Call<UserResponse> result = ApiClient.getUserService().getAllDataUser(idUsuarioP);
        result.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    criticasfilmefinal.add(new ItemCriticaFinal(idCriticaP, idFilmeP, idUsuarioP, textoCriticaP, notaCriticaP, dataCriticaP, qtdCurtidaCritica, response.body().getNickUsuario(), response.body().getIconUsuario()));
                    txtMsgCritica.setVisibility(View.GONE);
                    MyAdapterCriticas myAdapterCriticas = new MyAdapterCriticas(MainActivity.this, criticasfilmefinal);
                    LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, recyclerViewMain.VERTICAL, false);
                    recyclerViewMain.setLayoutManager(manager);
                    recyclerViewMain.setAdapter(myAdapterCriticas);

                    recyclerViewMain.addOnItemTouchListener(
                            new RecyclerItemClickListener(getApplicationContext(), recyclerViewMain, new RecyclerItemClickListener.OnItemClickListener(){
                                @Override
                                public void onItemClick(View view, int position) {
                                    usoMainUsu = true;
                                    dataCriticaUsu = criticasfilmefinal.get(position).getDataCritica();
                                    idCriticaUsu = criticasfilmefinal.get(position).getIdCritica();
                                    idFilmeUsu = criticasfilmefinal.get(position).getIdFilme();
                                    idUserUsu = criticasfilmefinal.get(position).getIdUsuario();
                                    textoCriticaUsu = criticasfilmefinal.get(position).getTextoCritica();
                                    posterFilmeUsu = itemsfilme.get(0).getPoster_path();
                                    dataFilmeUsu = itemsfilme.get(0).getRelease_date();
                                    fundoFilmeUsu = itemsfilme.get(0).getBackdrop_path();
                                    nomefilmeUsu = itemsfilme.get(0).getTitle();
                                    nickUsu = criticasfilmefinal.get(position).getNickUsuario();
                                    imgPerfilUsu = criticasfilmefinal.get(position).getIconUsuario();
                                    notaCriticaUsu = criticasfilmefinal.get(position).getNotaCritica();
                                    startActivity(new Intent(MainActivity.this, CriticaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
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
                    txtMsgCritica.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                StyleableToast.makeText(MainActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void buscarSessoes(String filmeID){
        Call<ArrayList<ItemSession>> result = ApiClient.getUserService().getSessionMovie(filmeID);
        result.enqueue(new Callback<ArrayList<ItemSession>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemSession>> call, Response<ArrayList<ItemSession>> response) {
                if(response.isSuccessful()){
                    sessionsdata = response.body();

                    if(sessionsdata.toString().equals("[]")){
                        txtMsgCritica.setVisibility(View.VISIBLE);
                        txtMsgCritica.setText("Parece que não temos\n sessões disponíveis");
                        btnSituFilme.setText("FORA DE CARTAZ");
                        btnSituFilme.setBackgroundColor(Color.parseColor("#AF0000"));
                    }
                    else {
                        btnSituFilme.setText("EM CARTAZ");
                        btnSituFilme.setBackgroundColor(Color.parseColor("#00AF51"));
                        Log.v("Ses ", sessionsdata.toString());

                        int b = 0;

                        final Map<String, ArrayList<ItemSession>> correlations3 = new LinkedHashMap<>();
                        for (int i = 0; i < sessionsdata.size(); i++) {
                            final String key = sessionsdata.get(i).getDataSessao();
                            if (correlations3.containsKey(key)) {
                                correlations3.get(key).add(new ItemSession(sessionsdata.get(i).getIdSessao(), sessionsdata.get(i).getIdFilme(), sessionsdata.get(i).getTokenCinema(), sessionsdata.get(i).getQtdIngressosSessao(), sessionsdata.get(i).getSalaSessao(), sessionsdata.get(i).getDataSessao(), sessionsdata.get(i).getHorarioSessao()));

                            } else {
                                final ArrayList<ItemSession> indexList = new ArrayList<>();
                                indexList.add(new ItemSession(sessionsdata.get(i).getIdSessao(), sessionsdata.get(i).getIdFilme(), sessionsdata.get(i).getTokenCinema(), sessionsdata.get(i).getQtdIngressosSessao(), sessionsdata.get(i).getSalaSessao(), sessionsdata.get(i).getDataSessao(), sessionsdata.get(i).getHorarioSessao()));
                                correlations3.put(key, indexList);
                                //datasiguais.add(new ItemSession(sessionsdata.get(i).idSessao, sessionsdata.get(i).idFilme, sessionsdata.get(i).getTokenCinema(), sessionsdata.get(i).getQtdIngressosSessao(), sessionsdata.get(i).getSalaSessao(), sessionsdata.get(i).getDataSessao(), sessionsdata.get(i).getHorarioSessao(), sessionsdata.get(i).getIngressos()));

                            }
                        }

                        MyAdapterDatas myAdapterDatas = new MyAdapterDatas(MainActivity.this, correlations3);
                        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, recyclerViewMain.HORIZONTAL, false);
                        recyclerViewMain.setLayoutManager(manager);
                        recyclerViewMain.setAdapter(myAdapterDatas);

                        ArrayList<String> key = new ArrayList<String>(correlations3.keySet());
                        ArrayList<ArrayList<ItemSession>> value = new ArrayList<ArrayList<ItemSession>>(correlations3.values());

                        for (int i = 0; i < value.get(0).size(); i++) {


                            buscarCinema(value.get(0).get(i).getIdSessao(), value.get(0).get(i).getIdFilme(), value.get(0).get(i).getTokenCinema(), value.get(0).get(i).getQtdIngressosSessao(), value.get(0).get(i).getSalaSessao(), value.get(0).get(i).getDataSessao(), value.get(0).get(i).getHorarioSessao());
                        }


                        recyclerViewMain.addOnItemTouchListener(
                                new RecyclerItemClickListener(getApplicationContext(), recyclerViewMain, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {

                                        sessionsfinalf.clear();

                                        ArrayList<String> key = new ArrayList<String>(correlations3.keySet());
                                        ArrayList<ArrayList<ItemSession>> value = new ArrayList<ArrayList<ItemSession>>(correlations3.values());

                                        for (int i = 0; i < value.get(position).size(); i++) {
                                            buscarCinema(value.get(position).get(i).getIdSessao(), value.get(position).get(i).getIdFilme(), value.get(position).get(i).getTokenCinema(), value.get(position).get(i).getQtdIngressosSessao(), value.get(position).get(i).getSalaSessao(), value.get(position).get(i).getDataSessao(), value.get(position).get(i).getHorarioSessao());
                                        }

                                    }

                                    @Override/*IDPopUp = items.get(position).getIdpop();
                                startActivity(new Intent(HomeActivity.this, PopUpActivity.class));*/
                                    public void onLongItemClick(View view, int position) {

                                        //Createpopupwindows();
                                    }
                                })
                        );

                    }

                }
                else{
                    txtMsgCritica.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemSession>> call, Throwable t) {
                StyleableToast.makeText(MainActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void buscarCinema(String idSessao, String idFilme, String tokenCin, String qtdIng, String salaSessao, String dataSessao, String horarioSessao){
        Call<CinemaResponse> result = ApiClient.getUserService().getCinemaData(tokenCin);
        result.enqueue(new Callback<CinemaResponse>() {
            @Override
            public void onResponse(Call<CinemaResponse> call, Response<CinemaResponse> response) {
                if(response.isSuccessful()){
                    sessionsfinalf.add(new ItemSessionRVF(idSessao, idFilme, tokenCin, response.body().getNomeCinema(), qtdIng, salaSessao, dataSessao, horarioSessao));

                    int b=0;

                    for (int i = 0; i < sessionsfinalf.size(); i++) {
                        salvarSessao();
                    }
                }
                else{
                    txtMsgCritica.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<CinemaResponse> call, Throwable t) {
                StyleableToast.makeText(MainActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void salvarSessao(){
        final Map<String, List<ItemSessaoF>> correlations2 = new LinkedHashMap<>();
        for (int i = 0; i < sessionsfinalf.size(); i++) {
            final String key = sessionsfinalf.get(i).getNomeCinema();
            if (correlations2.containsKey(key)) {
                correlations2.get(key).add(new ItemSessaoF(sessionsfinalf.get(i).getIdSessao(), sessionsfinalf.get(i).getTokenCinema(), sessionsfinalf.get(i).getDataSessao(), sessionsfinalf.get(i).getHorarioSessao(), sessionsfinalf.get(i).getIdFilme(), sessionsfinalf.get(i).getSalaSessao()));
                horariosfinais.add(new ChildModelClass2(key, sessionsfinalf.get(i).getHorarioSessao()));

            } else {
                final List<ItemSessaoF> indexList = new ArrayList<>();
                indexList.add(new ItemSessaoF(sessionsfinalf.get(i).getIdSessao(), sessionsfinalf.get(i).getTokenCinema(), sessionsfinalf.get(i).getDataSessao(), sessionsfinalf.get(i).getHorarioSessao(), sessionsfinalf.get(i).getIdFilme(), sessionsfinalf.get(i).getSalaSessao()));
                correlations2.put(key, indexList);

            }
        }

        ParentAdapter parentAdapter = new ParentAdapter(correlations2, MainActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, recyclerViewSessoes.VERTICAL, false);
        recyclerViewSessoes.setLayoutManager(manager);
        recyclerViewSessoes.setAdapter(parentAdapter);

    }


}