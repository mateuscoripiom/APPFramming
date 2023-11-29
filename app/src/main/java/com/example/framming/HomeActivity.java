package com.example.framming;

import static com.example.framming.LoginActivity.KEY_EMAIL;
import static com.example.framming.LoginActivity.KEY_ID;
import static com.example.framming.LoginActivity.SHARED_PREFS;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private EditText edittxtbusca;
    private ImageButton imgbtnpesquisa;
    private TextView txtnomeuser;
    public static List<Item> items = new ArrayList<>();
    public static List<ItemFinal> itemsfinal = new ArrayList<>();
    public static ArrayList<ItemNac> itemsnac = new ArrayList<ItemNac>();

    public static ArrayList<FilmesResponse> itemsfav = new ArrayList<>();

    public static String ID = null;
    public static String IDPopUp;
    public static RecyclerView recyclerViewPop, recyclerViewNac;
    public static int usadobtn = 0;
    public static String IDUser;

    public static String IDPositionPop;
    public static boolean swtPosition = false;

    public static String IDPositionPopTela;

    public static String nomeusuario;
    public static String nickusuario;
    public static String iconusuario;
    public static String tipoperfil;


    View layout;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageView imgbtndrawer;

    MenuInflater inflater;
    ImageView imgFundoFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        edittxtbusca = findViewById(R.id.edittxtbuscap);
        imgbtnpesquisa = findViewById(R.id.imgbtnpesquisa);
        txtnomeuser = findViewById(R.id.textView6);
        imgbtndrawer = findViewById(R.id.imgbtndrawer);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        buscarFavoritos();

        navigationView.bringToFront();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();

        layout = findViewById(R.id.constraint);
        buscaInfoUser();
        buscarNacionais();

        imgbtndrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String homenav = item.getTitle().toString();
                switch (homenav) {
                    case "Início":
                        itemsfinal.clear();
                        items.clear();
                        startActivity(new Intent(HomeActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        break;
                    case "Perfil":
                        startActivity(new Intent(HomeActivity.this, ProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        break;
                    case "Recompensas":
                        startActivity(new Intent(HomeActivity.this, RecompensasActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        break;
                    case "Diário":
                        startActivity(new Intent(HomeActivity.this, DiaryActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        break;
                    case "Quero Ver":
                        startActivity(new Intent(HomeActivity.this, QueroVerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                        break;
                    case "Deslogar":
                        deslogarBox();
                        break;
                }
                return false;
            }


        });


        edittxtbusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PesquisaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

        recyclerViewPop = findViewById(R.id.recyclerViewPop);
        recyclerViewNac = findViewById(R.id.recyclerViewNac);
        buscaInfoFilmePopular();
        Switch swthome = findViewById(R.id.swthome);
        swthome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    swtPosition = true;
                    items.clear();
                    itemsfinal.clear();
                    buscaInfoFilmeEmBreve();
                } else {
                    swtPosition = false;
                    items.clear();
                    itemsfinal.clear();
                    buscaInfoFilmePopular();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    private void deslogarBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString(KEY_EMAIL, null);
        String idu = sharedPreferences.getString(KEY_ID, null);
        if (check != null && idu != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            StyleableToast.makeText(HomeActivity.this, "Deslogado com sucesso!", Toast.LENGTH_LONG, R.style.deslogarToast).show();
            finish();
            startActivity(new Intent(HomeActivity.this, AberturaActivity.class));

        }
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
                if (response.code() != 200) {
                    //Toast.makeText(MainActivity.this, "Cheque sua conexão", Toast.LENGTH_SHORT).show();

                }
                if (response.isSuccessful()) {
                    String nomeusu = "";
                    String username = "";
                    String linkfotoperfil = "";
                    String tipousuario = "";

                    nomeusu = response.body().getNomeUsuario();
                    username = response.body().getNickUsuario();
                    linkfotoperfil = response.body().getIconUsuario();
                    tipousuario = response.body().getTipoUsuario();

                    if (nomeusu != null || nomeusu != "") {
                        txtnomeuser.setText("Olá, " + nomeusu + "!");
                        nomeusuario = nomeusu;
                        nickusuario = username;
                        iconusuario = linkfotoperfil;
                        tipoperfil = tipousuario;
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
        if (usadobtn != 0) {
            String queryString = "";
            if (args != null) {
                queryString = args.getString("queryString");
            }
            return new CarregaFilme(this, queryString);
        }
        if (swtPosition == true) {
            items.clear();
            return new CarregaEmBreve(this);
        } else {
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
                    buscarPosterFav(idfilme, posterfilme, z);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // move para a proxima linha
                z++;
            }

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
        if (usadobtn != 0) {
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

    public void buscarPosterFav(String filmeID, String linkPoster, Integer ordemArray) {
        Call<PosterResponse> result = ApiClient.getUserService().getAllPosters(HomeActivity.IDUser, filmeID);
        result.enqueue(new Callback<PosterResponse>() {
            @Override
            public void onResponse(Call<PosterResponse> call, Response<PosterResponse> response) {
                if (response.code() != 200) {
                    itemsfinal.add(new ItemFinal(filmeID, linkPoster, ordemArray));

                }
                if (response.isSuccessful()) {
                    itemsfinal.add(new ItemFinal(filmeID, response.body().getLinkPoster(), ordemArray));
                    //Log.v("Tag", "The title" + itemsfavfinal.get(1).getPosterFilme().toString());
                }
                sortListByArrayPop(itemsfinal);
                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, false);
                recyclerViewPop.setLayoutManager(horizontalLayoutManager);
                recyclerViewPop.setAdapter(new MyAdapterPop(getApplicationContext(), itemsfinal));

                recyclerViewPop.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), recyclerViewPop, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                IDPositionPop = itemsfinal.get(position).getIdpop();
                                startActivity(new Intent(HomeActivity.this, MainActivity.class));
                            }

                            @Override/*IDPopUp = items.get(position).getIdpop();
                                startActivity(new Intent(HomeActivity.this, PopUpActivity.class));*/
                            public void onLongItemClick(View view, int position) {

                                //Createpopupwindows();
                            }
                        })
                );
            }

            @Override
            public void onFailure(Call<PosterResponse> call, Throwable t) {
                StyleableToast.makeText(HomeActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    private void sortListByArrayPop(List<ItemFinal> theArrayListEvents) {
        Collections.sort(theArrayListEvents, new EventDetailSortByArrayPop());
    }

    private void Createpopupwindows() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popUpView = inflater.inflate(R.layout.activity_pop_up, null);

        int width = ViewGroup.LayoutParams.MATCH_PARENT;
        int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        PopupWindow popupWindow = new PopupWindow(popUpView, width, height, focusable);
        layout.post(new Runnable() {
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
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void buscarNacionais() {
        Call<ArrayList<ItemNac>> result = ApiClient.getUserService().getAllNational();
        result.enqueue(new Callback<ArrayList<ItemNac>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemNac>> call, Response<ArrayList<ItemNac>> response) {
                if (response.isSuccessful()) {
                    itemsnac = response.body();

                    int b = 0;

                    for (b = 0; b < itemsnac.size(); b++) {
                        MyAdapterNac myAdapterNac = new MyAdapterNac(HomeActivity.this, itemsnac);
                        LinearLayoutManager manager = new LinearLayoutManager(HomeActivity.this, recyclerViewNac.HORIZONTAL, false);
                        recyclerViewNac.setLayoutManager(manager);
                        recyclerViewNac.setAdapter(myAdapterNac);
                    }

                    recyclerViewNac.addOnItemTouchListener(
                            new RecyclerItemClickListener(getApplicationContext(), recyclerViewNac, new RecyclerItemClickListener.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    IDPositionPop = itemsnac.get(position).getId();

                                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                                }

                                @Override/*IDPopUp = items.get(position).getIdpop();
                                startActivity(new Intent(HomeActivity.this, PopUpActivity.class));*/
                                public void onLongItemClick(View view, int position) {

                                    //Createpopupwindows();
                                }
                            })
                    );

                } else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemNac>> call, Throwable t) {
                StyleableToast.makeText(HomeActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void buscarFavoritos() {
        Call<ArrayList<FilmesResponse>> result = ApiClient.getUserService().getFavoritos(HomeActivity.IDUser);
        result.enqueue(new Callback<ArrayList<FilmesResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<FilmesResponse>> call, Response<ArrayList<FilmesResponse>> response) {
                if (response.isSuccessful()) {
                    itemsfav = response.body();

                    imgFundoFav = findViewById(R.id.imageView14);
                    Picasso
                            .get()
                            .load("https://www.themoviedb.org/t/p/original" + itemsfav.get(0).getBackdrop_path())
                            .into(imgFundoFav);
                } else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<FilmesResponse>> call, Throwable t) {
                StyleableToast.makeText(HomeActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });

    }
}