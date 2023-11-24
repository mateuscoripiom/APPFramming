package com.example.framming;

import androidx.activity.OnBackPressedCallback;
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

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Favorito1Activity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    public static List<ItemBusca> itemsbusca = new ArrayList<>();
    RecyclerView recyclerViewFav1;
    EditText etxtbuscarfav1;
    ImageButton imgbtnbuscarfav1;
    public static String IDfav1, nomefav1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorito1);

        recyclerViewFav1 = findViewById(R.id.recyclerViewFav1);
        etxtbuscarfav1 = findViewById(R.id.edittxtbuscarfav1);
        imgbtnbuscarfav1 = findViewById(R.id.imgbtnbuscarfav1);

        etxtbuscarfav1.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                etxtbuscarfav1.clearFocus();
                itemsbusca.clear();
                startActivity(new Intent(Favorito1Activity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        imgbtnbuscarfav1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemsbusca.clear();
                recyclerViewFav1 = findViewById(R.id.recyclerViewFav1);
                buscaInfoFilmeQ();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(imgbtnbuscarfav1.getWindowToken(), 0);
            }
        });

        etxtbuscarfav1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    itemsbusca.clear();
                    recyclerViewFav1 = findViewById(R.id.recyclerViewPesquisa);
                    buscaInfoFilmeQ();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(imgbtnbuscarfav1.getWindowToken(), 0);
                }
                return false;
            }
        });

    }

    public void buscaInfoFilmeQ() {
        // Recupera a string de busca.
        String queryString = etxtbuscarfav1.getText().toString();
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
                Toast.makeText(Favorito1Activity.this, "Termo inválido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Favorito1Activity.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
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
            recyclerViewFav1.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewFav1.setAdapter(new MyAdapterPesquisa(getApplicationContext(), itemsbusca));

            recyclerViewFav1.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), recyclerViewFav1, new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            IDfav1 = itemsbusca.get(position).getIdbusca();
                            nomefav1 = itemsbusca.get(position).getNamebusca();
                            saveFavorito(createFavRequest(IDfav1));

                            //startActivity(new Intent(PesquisaActivity.this, MainActivity.class));
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
                Toast.makeText(Favorito1Activity.this, "Sem retorno de dados", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(Favorito1Activity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada

    }

    public FavoritoRequest createFavRequest(String favID){
        FavoritoRequest favoritoRequest = new FavoritoRequest();
        favoritoRequest.setIdMovie(favID);

        return favoritoRequest;
    }

    public void saveFavorito(FavoritoRequest favoritoRequest){
        Call<ResponseBody> result = ApiClient.getUserService().salvarFilmeFav(HomeActivity.IDUser, favoritoRequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    StyleableToast.makeText(Favorito1Activity.this,nomefav1 + " salvo como primeiro favorito!", Toast.LENGTH_LONG, R.style.exampleToast).show();
                }
                else{
                    Toast.makeText(Favorito1Activity.this, "Salvamento falhou", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Favorito1Activity.this, "Salvamento falhou" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}