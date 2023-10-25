package com.example.framming;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText edittxtbusca;
    private ImageButton imgbtnpesquisa;
    private TextView txtnomeuser;
    public static List<Item> items = new ArrayList<>();

    public static String ID = null;
    public static String IDPopUp;
    public static RecyclerView recyclerViewPop;
    public static int usadobtn = 0;
    public static String IDUser;

    public static String IDPositionPop;
    public static boolean swtPosition = false;

    public static String IDPositionPopTela;

    public static String nomeusuario;


    View layout;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imgbtndrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.bringToFront();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    if(item == 1){
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }
                    case R.id.nav_perfil:{
                        startActivity(new Intent(HomeActivity.this, ProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }
                }
                return false;
            }
        });

        edittxtbusca = findViewById(R.id.edittxtbuscap);
        imgbtnpesquisa = findViewById(R.id.imgbtnpesquisa);
        txtnomeuser = findViewById(R.id.textView6);
        imgbtndrawer = findViewById(R.id.imgbtndrawer);

        layout = findViewById(R.id.constraint);
        buscaInfoUser();

        imgbtndrawer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                drawerLayout.openDrawer(Gravity.LEFT);

            }
        });


        edittxtbusca.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, PesquisaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

        recyclerViewPop = findViewById(R.id.recyclerViewPop);
        buscaInfoFilmePopular();
        Switch swthome = findViewById(R.id.swthome);
        swthome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    swtPosition = true;
                    items.clear();
                    buscaInfoFilmeEmBreve();
                }
                else{
                    swtPosition = false;
                    items.clear();
                    buscaInfoFilmePopular();
                }
            }
        });

    }

    public void buscaInfoFilmePopular() {
        // Verifica o status da conexão de rede
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        /* Se a rede estiver disponivel e o campo de busca não estiver vazio
         iniciar o Loader CarregaLivros */
        if (networkInfo != null && networkInfo.isConnected()) {
            Bundle queryBundle = new Bundle();
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            Toast.makeText(HomeActivity.this, "Termo inválido", Toast.LENGTH_SHORT).show();
        }
    }
    public void buscaInfoFilmeEmBreve() {
        // Verifica o status da conexão de rede
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        /* Se a rede estiver disponivel e o campo de busca não estiver vazio
         iniciar o Loader CarregaLivros */
        if (networkInfo != null && networkInfo.isConnected()) {
            Bundle queryBundle = new Bundle();
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            Toast.makeText(HomeActivity.this, "Termo inválido", Toast.LENGTH_SHORT).show();
        }
    }

    public void buscaInfoUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://framming-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);

        Call<UserResponse> call = userService.getAllDataUser(IDUser);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.code() != 200){
                    //Toast.makeText(MainActivity.this, "Cheque sua conexão", Toast.LENGTH_SHORT).show();

                }
                if(response.isSuccessful()) {
                    String nomeusu = "";

                    nomeusu = response.body().getNomeUsuario();

                    if (nomeusu != null || nomeusu != "") {
                        txtnomeuser.setText("Olá, " + nomeusu + "!");
                        nomeusuario = nomeusu;
                    }
                }
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        if(usadobtn != 0) {
            String queryString = "";
            if (args != null) {
                queryString = args.getString("queryString");
            }
            return new CarregaFilme(this, queryString);
        }
        if(swtPosition == true){
            items.clear();
            return new CarregaEmBreve(this);
        }
        else {
            items.clear();
            return new CarregaPopulares(this);
        }
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
            String idfilme = null;
            String nomefilme = null;
            String posterfilme = null;
            while (z < itemsArray.length()) {
                // Obtem a informação
                JSONObject id = itemsArray.getJSONObject(z);
                // JSONObject volumeInfo = genre.getJSONObject("id");
                //Toast.makeText(this, genre.toString(), Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    idfilme = id.getString("id");
                    nomefilme = id.getString("title");
                    posterfilme = id.getString("poster_path");
                    //Toast.makeText(this, idfilme.toString(), Toast.LENGTH_SHORT).show();
                    Log.v("Tag", "The title" + nomefilme.toString());
                    items.add(new Item(idfilme, posterfilme));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // move para a proxima linha
                z++;
            }
                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerViewPop.setLayoutManager(horizontalLayoutManager);
                recyclerViewPop.setAdapter(new MyAdapterPop(getApplicationContext(), items));

                recyclerViewPop.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), recyclerViewPop, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                IDPositionPop = items.get(position).getIdpop();
                                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                            }

                            @Override/*IDPopUp = items.get(position).getIdpop();
                                startActivity(new Intent(HomeActivity.this, PopUpActivity.class));*/
                            public void onLongItemClick(View view, int position) {

                                //Createpopupwindows();
                            }
                        })
                );
                //mostra o resultado qdo possivel.
            /*if (idfilme != "") {
                ID = idfilme;
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(HomeActivity.this, "Sem retorno de dados", Toast.LENGTH_SHORT).show();
            }*/
            } catch (Exception e) {
                // Se não receber um JSOn valido, informa ao usuário
                Toast.makeText(HomeActivity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
        if(usadobtn != 0){
        try {
            // Converte a resposta em Json
            JSONObject jsonObject = new JSONObject(data);
            // Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
            // Obtem o JSONArray dos itens de livros
            JSONArray itemsArray = jsonObject.getJSONArray("results");
            int z = 0;
            String idfilme = null;
            while (z < itemsArray.length() &&
                    (idfilme == null)) {
                // Obtem a informação
                JSONObject id = itemsArray.getJSONObject(z);
                // JSONObject volumeInfo = genre.getJSONObject("id");
                //Toast.makeText(this, genre.toString(), Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    idfilme = id.getString("id");
                    //Toast.makeText(this, idfilme.toString(), Toast.LENGTH_SHORT).show();
                    Log.v("Tag", "The title" + id.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // move para a proxima linha
                z++;
            }
            //mostra o resultado qdo possivel.
            if (idfilme != "") {
                ID = idfilme;
                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(HomeActivity.this, "Sem retorno de dados", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(HomeActivity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
        }
    }

    private void Createpopupwindows() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.activity_pop_up, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable(){
            @Override
            public void run() {
                popupWindow.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
            }
            });
        }


    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }

    @Override
    public void onBackPressed(){
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

}