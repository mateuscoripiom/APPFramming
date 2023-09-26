package com.example.framming;

import static com.example.framming.HomeActivity.items;
import static com.example.framming.HomeActivity.swtPosition;
import static com.example.framming.PesquisaActivity.itemsbusca;
import static com.example.framming.PosterActivity.posterArray;

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

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public static ImageView imgFundo, imgPoster;
    private TextView txtName, txtAno, txtDuracao, txtSinopse;
    private Button btnGenero, btnPesquisa, btndiario;
    public static EditText etxtID;
    private ImageButton imgbtnposter, imgbtnvoltar;
    public static boolean usado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgFundo = findViewById(R.id.imgFundo);
        imgPoster = findViewById(R.id.imgPoster);
        txtName = findViewById(R.id.txtName);
        txtAno = findViewById(R.id.txtAno);
        txtDuracao = findViewById(R.id.txtDuracao);
        txtSinopse = findViewById(R.id.txtSinopse);
        btnGenero = findViewById(R.id.btnGenero);
        imgbtnposter = findViewById(R.id.imgbtnposter);
        imgbtnvoltar = findViewById(R.id.imgbtnvoltar);
        btndiario = findViewById(R.id.btndiario);

        buscaInfoFilme();
        imgbtnposter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, PosterActivity.class));
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
                usado = false;
                PesquisaActivity.IDpesquisa = null;
                HomeActivity.usadobtn = 0;
                posterArray.clear();
                itemsbusca.clear();
                items.clear();
                swtPosition = false;
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }
    public void buscaInfoFilme() {
        // Recupera a string de busca.
        String movieString = null;
        if(HomeActivity.ID != null) {
            movieString = HomeActivity.ID;
        }
        if(HomeActivity.IDPositionPop != null){
            movieString = HomeActivity.IDPositionPop;
        }
        if(HomeActivity.IDPositionPop != null){
            movieString = HomeActivity.IDPositionPop;
        }
        if(PesquisaActivity.IDpesquisa != null){
            movieString = PesquisaActivity.IDpesquisa;
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
            // Procura pro resultados nos itens do array
            while (i < jsonObject.length() &&
                    (nome == null) && (imgfundo == null) && (imgposter == null) && (sinopse == null) && (duracao == null) && (ano == null)) {
                // Obtem a informação
                Object title = jsonObject.get("title"); // pega o title no object json
                Object backdrop = jsonObject.get("backdrop_path");
                Object poster = jsonObject.get("poster_path");
                Object overview = jsonObject.get("overview");
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
            if ((nome != null) && (imgfundo != null) && (imgposter != null) && (sinopse != null) && (duracao != null) && (ano != null) && (ngenero != null)) {
                txtName.setText(nome);
                txtDuracao.setText(duracao + "min");
                txtAno.setText(ano);
                btnGenero.setText(ngenero);
                if(sinopse == ""){
                    txtSinopse.setText(buscaInfoFilmeEN.filmesinen);
                }
                else{
                    txtSinopse.setText(sinopse);
                }
                if(imgfundo == ""){
                    imgFundo.setVisibility(View.GONE);
                }
                else {
                    Picasso
                            .get()
                            .load("https://www.themoviedb.org/t/p/original" + imgfundo)
                            .into(imgFundo);
                }
                if(usado ==  false) {
                    Picasso
                            .get()
                            .load("https://www.themoviedb.org/t/p/original" + imgposter)
                            .into(imgPoster);
                } else{
                    Picasso
                            .get()
                            .load("https://www.themoviedb.org/t/p/original" + PosterActivity.IDPosition)
                            .into(imgPoster);
                }



            } else {
                Toast.makeText(MainActivity.this, "Sem retorno de dados", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(MainActivity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }

}