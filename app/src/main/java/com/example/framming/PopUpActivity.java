package com.example.framming;

import static com.example.framming.MainActivity.usado;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PopUpActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    public static TextView txtAnoPopUp, txtNamePopUp, txtDuracaoPopUp;
    public static ImageView imgPosterPopUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        imgPosterPopUp = findViewById(R.id.imgPosterPopUp);
        txtNamePopUp = findViewById(R.id.txtNamePopUp);
        txtAnoPopUp = findViewById(R.id.txtAnoPopUp);
        txtDuracaoPopUp = findViewById(R.id.txtDuracaoPopUp);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(PopUpActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        buscaInfoFilme();
    }

    public void buscaInfoFilme() {
        // Recupera a string de busca.
        String movieString = HomeActivity.IDPopUp;
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
                Toast.makeText(PopUpActivity.this, "Termo inválido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PopUpActivity.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
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
            String imgposter = null;
            String duracao = null;
            String ano = null;
            // Procura pro resultados nos itens do array
            while (i < jsonObject.length() &&
                    (nome == null) && (imgposter == null) && (duracao == null) && (ano == null)) {
                // Obtem a informação
                Object title = jsonObject.get("title"); // pega o title no object json
                Object poster = jsonObject.get("poster_path");
                Object runtime = jsonObject.get("runtime");
                Object year = jsonObject.get("release_date");

                // Toast.makeText(this, "MOVIE:" + title, Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    nome = title.toString();
                    imgposter = poster.toString();
                    duracao = runtime.toString();
                    ano = year.toString();
                    // Toast.makeText(this, "NOME:" + nome, Toast.LENGTH_SHORT).show();
                } catch (Exception err) {
                    err.printStackTrace();
                }
                // move para a proxima linha
                i++;
            }
            if ((nome != null) && (imgposter != null) && (duracao != null) && (ano != null)) {
                txtNamePopUp.setText(nome);
                txtDuracaoPopUp.setText(duracao + "min");
                txtAnoPopUp.setText(ano);
                if(usado ==  false) {
                    Picasso
                            .get()
                            .load("https://www.themoviedb.org/t/p/original" + imgposter)
                            .into(imgPosterPopUp);
                } else{
                    Picasso
                            .get()
                            .load("https://www.themoviedb.org/t/p/original" + PosterActivity.IDPosition)
                            .into(imgPosterPopUp);
                }



            } else {
                Toast.makeText(PopUpActivity.this, "Sem retorno de dados", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(PopUpActivity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }
}