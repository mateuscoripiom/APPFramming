package com.example.framming;

import static com.example.framming.MainActivity.etxtID;
import static com.example.framming.MainActivity.imgPoster;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PosterActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private ImageView imgPoster2, imgFundo2;
    public static String IDPosition;
    public static ArrayList<String> posterArray = new ArrayList<>();
    private ImageButton btnvoltar2;
    public static String iposters;
    public static String IDFilme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster);

        imgFundo2 = findViewById(R.id.imgFundo3);
        btnvoltar2 = findViewById(R.id.imgbtnvoltar2);

        buscaInfoFilmePoster();

        btnvoltar2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(PosterActivity.this, MainActivity.class));
                posterArray.clear();
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(PosterActivity.this, MainActivity.class));
                posterArray.clear();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
    public void buscaInfoFilmePoster() {
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
        if(PesquisaActivity.IDpesquisa != null){
            movieString = PesquisaActivity.IDpesquisa;
            IDFilme = movieString;
        }
        // esconde o teclado qdo o botão é clicado
        /*InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } */

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
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            if (movieString.length() == 0) {
                Toast.makeText(PosterActivity.this, "Termo inválido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PosterActivity.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
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
        return new CarregaPoster(this, movieString);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            // Converte a resposta em Json
            JSONObject jsonObject = new JSONObject(data);
            // Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
            // Obtem o JSONArray dos itens de livros
            // JSONArray itemsArray = jsonObject.getJSONArray("genres");
            /// Toast.makeText(this, itemsArray.toString(), Toast.LENGTH_SHORT).show();
            // inicializa o contador

            JSONArray itemsArray = jsonObject.getJSONArray("posters");
            
            int z = 0;
            String iposter = null;
            while (z < itemsArray.length()/* &&
                    (iposter == null)*/) {
                // Obtem a informação
                JSONObject poster = itemsArray.getJSONObject(z);
                // Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();


                // JSONObject volumeInfo = genre.getJSONObject("name");
                //Toast.makeText(this, genre.toString(), Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                /*try {
                    iposter = poster.getString("file_path");
                    //Toast.makeText(this, ngenero.toString(), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                iposter = poster.getString("file_path");
                posterArray.add(poster.getString("file_path"));


                Log.v("Tag", "Poster " + posterArray);
                ImageView imageView = new ImageView(PosterActivity.this);

                /*Picasso
                        .get()
                        .load("https://www.themoviedb.org/t/p/original" + iposter)
                        .into(imgPoster2);*/

                // move para a proxima linha
                z++;
            }
            RecyclerView recyclerView = findViewById(R.id.recyclerView);

            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            recyclerView.setAdapter(new MyAdapter(getApplicationContext(),posterArray));

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                        @Override public void onItemClick(View view, int position) {
                            MainActivity.usado = true;
                            IDPosition = posterArray.get(position);
                            //NetworkUtils.salvaPoster(HomeActivity.IDUser, IDFilme, IDPosition);
                            startActivity(new Intent(PosterActivity.this, MainActivity.class));
                        }

                        @Override public void onLongItemClick(View view, int position) {
                            // do whatever
                        }
                    })
            );
            JSONArray itemsArrayB = jsonObject.getJSONArray("backdrops");
            int y = 0;
            String ifundo = null;
            while (y < itemsArray.length() &&
                    (ifundo == null)) {
                // Obtem a informação
                JSONObject backdrop = itemsArrayB.getJSONObject(y);

                // JSONObject volumeInfo = genre.getJSONObject("name");
                //Toast.makeText(this, genre.toString(), Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    ifundo = backdrop.getString("file_path");
                    //Toast.makeText(this, ngenero.toString(), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // move para a proxima linha
                y++;
            }
            //mostra o resultado qdo possivel.
            if (/*(iposter != null) &&*/ (ifundo != null)) {
                /*Picasso
                        .get()
                        .load("https://www.themoviedb.org/t/p/original" + iposter)
                        .into(imgPoster2);*/
                Picasso
                        .get()
                        .load("https://www.themoviedb.org/t/p/original" + ifundo)
                        .into(imgFundo2);

            } else {
                Toast.makeText(PosterActivity.this, "Sem retorno de dados", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(PosterActivity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }
}