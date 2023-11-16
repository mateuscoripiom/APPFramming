package com.example.framming;

import static com.example.framming.LoginActivity.KEY_EMAIL;
import static com.example.framming.LoginActivity.KEY_ID;
import static com.example.framming.LoginActivity.SHARED_PREFS;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;

public class PesquisaActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private EditText edittxtbuscar;
    public TextView txtnomeusupesquisa;
    private ImageButton imgbtnbuscar;
    public static String IDpesquisa = null;
    public static RecyclerView recyclerViewbusca;
    public static List<ItemBusca> itemsbusca = new ArrayList<>();

    View layout;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imgbtndrawer2;

    MenuInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisa);

        edittxtbuscar = findViewById(R.id.edittxtbuscar);
        imgbtnbuscar = findViewById(R.id.imgbtnbuscar);
        txtnomeusupesquisa = findViewById(R.id.textViewnomeusu);
        imgbtndrawer2 = findViewById(R.id.imgbtndrawer2);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.bringToFront();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        layout = findViewById(R.id.constraintl);

        imgbtndrawer2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                drawerLayout.openDrawer(Gravity.LEFT);
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(imgbtnbuscar.getWindowToken(), 0);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String homenav = item.getTitle().toString();
                switch (homenav){
                    case "Início":
                        startActivity(new Intent(PesquisaActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        break;
                    case "Perfil":
                        startActivity(new Intent(PesquisaActivity.this, ProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        break;
                    case "Recompensas":
                        startActivity(new Intent(PesquisaActivity.this, RecompensasActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        break;
                    case "Diário":
                        startActivity(new Intent(PesquisaActivity.this, DiaryActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        break;
                    case "Deslogar":
                        deslogarBox();
                        break;
                }
                return false;
            }


        });

        txtnomeusupesquisa.setText("Olá, " + HomeActivity.nomeusuario + "!");

        edittxtbuscar.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                edittxtbuscar.clearFocus();
                itemsbusca.clear();
                startActivity(new Intent(PesquisaActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        imgbtnbuscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                itemsbusca.clear();
                recyclerViewbusca = findViewById(R.id.recyclerViewPesquisa);
                buscaInfoFilmeQ();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(imgbtnbuscar.getWindowToken(), 0);
            }
        });

        edittxtbuscar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    itemsbusca.clear();
                    recyclerViewbusca = findViewById(R.id.recyclerViewPesquisa);
                    buscaInfoFilmeQ();
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(imgbtnbuscar.getWindowToken(), 0);
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

    private void deslogarBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString(KEY_EMAIL, null);
        String idu = sharedPreferences.getString(KEY_ID, null);
        if(check != null && idu != null){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            StyleableToast.makeText(PesquisaActivity.this, "Deslogado com sucesso!", Toast.LENGTH_LONG, R.style.deslogarToast).show();
            finish();
            startActivity(new Intent(PesquisaActivity.this, AberturaActivity.class));

        }
    }
}