package com.example.framming;

import static com.example.framming.HomeActivity.items;
import static com.example.framming.HomeActivity.swtPosition;
import static com.example.framming.PesquisaActivity.itemsbusca;
import static com.example.framming.PosterActivity.posterArray;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public static ImageView imgPoster;
    public static ImageView imgBackground;
    private TextView txtName, txtAno, txtDuracao, txtSinopse, txtNomeOriginal;
    private Button btnGenero, btnPesquisa, btndiario, btnhorarioses;
    public static EditText etxtID;
    private ImageButton imgbtnposter, imgbtnvoltar;
    public static boolean usado = false;
    public static boolean usadoEscolha = false;
    public static boolean b = false;
    public static boolean c = false;
    public static int IDLoader;
    public static String IDFilme;
    public static String linkFilmeSalvo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);

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



        buscaPosterSalvo();

        btnhorarioses.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, tipoingressoActivity.class));
            }
        });

        imgbtnposter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, PosterActivity.class));
                PosterActivity.IDPosition = null;
                MainActivity.linkFilmeSalvo = null;
                finish();
            }
        });

        btndiario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, LogActivity.class));
                finish();
            }
        });

        imgbtnvoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                HomeActivity.ID = null;
                HomeActivity.IDPositionPop = null;
                PosterActivity.IDPosition = null;
                MainActivity.linkFilmeSalvo = null;
                usado = false;
                usadoEscolha = false;
                PesquisaActivity.IDpesquisa = null;
                HomeActivity.usadobtn = 0;
                posterArray.clear();
                itemsbusca.clear();
                items.clear();
                swtPosition = false;
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
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
                PesquisaActivity.IDpesquisa = null;
                HomeActivity.usadobtn = 0;
                posterArray.clear();
                itemsbusca.clear();
                MainActivity.linkFilmeSalvo = null;
                items.clear();
                swtPosition = false;
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
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
                    txtDuracao.setText(duracao + "min");
                    txtAno.setText(ano);
                    txtNomeOriginal.setText(nomeoriginal);
                    btnGenero.setText(ngenero);
                    if (sinopse == "") {
                        txtSinopse.setText(buscaInfoFilmeEN.filmesinen);
                    } else {
                        txtSinopse.setText(sinopse);
                    }
                    Picasso
                            .get()
                            .load("https://www.themoviedb.org/t/p/original" + imgfundo)
                            .into(imgBackground);

                    if (usadoEscolha == true) {
                        Picasso
                                .get()
                                .load("https://www.themoviedb.org/t/p/original" + linkFilmeSalvo)
                                .into(imgPoster);

                    } else {
                        Picasso
                                .get()
                                .load("https://www.themoviedb.org/t/p/original" + imgposter)
                                .into(imgPoster);
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

}