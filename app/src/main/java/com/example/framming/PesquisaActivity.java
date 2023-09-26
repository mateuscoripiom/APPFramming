package com.example.framming;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PesquisaActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private EditText edittxtbuscar;
    private ImageButton imgbtnbuscar;
    public static String IDpesquisa = null;
    public static RecyclerView recyclerViewbusca;
    public static List<ItemBusca> itemsbusca = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        edittxtbuscar = findViewById(R.id.edittxtbuscar);
        imgbtnbuscar = findViewById(R.id.imgbtnbuscar);

        edittxtbuscar.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

        imgbtnbuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemsbusca.clear();
                recyclerViewbusca = findViewById(R.id.recyclerViewPesquisa);
                buscaInfoFilmeQ();
            }
        });

        edittxtbuscar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    itemsbusca.clear();
                    recyclerViewbusca = findViewById(R.id.recyclerViewPesquisa);
                    buscaInfoFilmeQ();
                }
                return false;
            }
        });
    }

    public void buscaInfoFilmeQ() {
        // Recupera a string de busca.
        String queryString = edittxtbuscar.getText().toString();
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
                && queryString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            if (queryString.length() == 0) {
                Toast.makeText(PesquisaActivity.this, "Termo inválido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(PesquisaActivity.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if (args != null) {
            queryString = args.getString("queryString");
        }
        return new CarregaFilme(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        try {
            // Converte a resposta em Json
            JSONObject jsonObject = new JSONObject(data);
            // Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
            // Obtem o JSONArray dos itens de livros
            JSONArray itemsArray = jsonObject.getJSONArray("results");
            int z = 0;
            String idbusca = null;
            String imgbusca = null;
            String namebusca = null;
            String originalname = null;
            String anobusca = null;
            String sinopsebusca = null;
            while (z < itemsArray.length()) {
                // Obtem a informação
                JSONObject id = itemsArray.getJSONObject(z);
                // JSONObject volumeInfo = genre.getJSONObject("id");
                //Toast.makeText(this, genre.toString(), Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    idbusca = id.getString("id");
                    imgbusca = id.getString("poster_path");
                    namebusca = id.getString("title");
                    originalname = id.getString("original_title");
                    anobusca = id.getString("release_date");
                    sinopsebusca = id.getString("overview");
                    itemsbusca.add(new ItemBusca(idbusca, imgbusca, namebusca, originalname, anobusca, sinopsebusca));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // move para a proxima linha
                z++;
            }
            recyclerViewbusca.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewbusca.setAdapter(new MyAdapterPesquisa(getApplicationContext(), itemsbusca));

            recyclerViewbusca.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), recyclerViewbusca, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            IDpesquisa = itemsbusca.get(position).getIdbusca();
                            startActivity(new Intent(PesquisaActivity.this, MainActivity.class));
                        }

                        @Override
                        public void onLongItemClick(View view, int position) {
                            // do whatever
                        }
                    })
            );
            //mostra o resultado qdo possivel.
            if (idbusca != "") {
            } else {
                Toast.makeText(PesquisaActivity.this, "Sem retorno de dados", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(PesquisaActivity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }
}