package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListasActivity extends AppCompatActivity {

    Button btnCriarLista;
    RecyclerView recyclerViewListas;
    public static String IDLista;

    public static ArrayList<ListaResponse> itemslista = new ArrayList<>();

    ImageButton imgbtnvoltar18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listas);

        btnCriarLista = findViewById(R.id.btnCriarLista);
        recyclerViewListas = findViewById(R.id.recyclerViewListas);
        imgbtnvoltar18 = findViewById(R.id.imgbtnvoltar18);

        buscarListas();

        btnCriarLista.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ListasActivity.this, CriarListaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });
        if(MainActivity.addLista == true){
            imgbtnvoltar18.setVisibility(View.GONE);
        }


    }

    public void buscarListas(){
        Call<ArrayList<ListaResponse>> result = ApiClient.getUserService().getAllLists(HomeActivity.IDUser);
        result.enqueue(new Callback<ArrayList<ListaResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<ListaResponse>> call, Response<ArrayList<ListaResponse>> response) {
                if(response.isSuccessful()){
                    itemslista = response.body();

                    int b=0;

                    for(b=0; b<itemslista.size(); b++){
                        MyAdapterListas myAdapterListas = new MyAdapterListas(ListasActivity.this, itemslista);
                        LinearLayoutManager manager = new LinearLayoutManager(ListasActivity.this, recyclerViewListas.VERTICAL, false);
                        recyclerViewListas.setLayoutManager(manager);
                        recyclerViewListas.setAdapter(myAdapterListas);

                        recyclerViewListas.addOnItemTouchListener(
                                new RecyclerItemClickListener(getApplicationContext(), recyclerViewListas, new RecyclerItemClickListener.OnItemClickListener(){
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        if(MainActivity.addLista == true){
                                            saveFLista(itemslista.get(position).getIdLista(), createFLista());
                                            startActivity(new Intent(ListasActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                            MainActivity.addLista = false;
                                        }
                                        else{
                                            IDLista = itemslista.get(position).getIdLista();
                                            startActivity(new Intent(ListasActivity.this, FilmesListaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                                            finish();
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

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ListaResponse>> call, Throwable t) {
                StyleableToast.makeText(ListasActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public FListaRequest createFLista(){
        FListaRequest fListaRequest = new FListaRequest();
        fListaRequest.setIdMovie(MainActivity.IDFilme);

        return fListaRequest;
    }

    public void saveFLista(String IDLista, FListaRequest fListaRequest){
        Call<ResponseBody> result = ApiClient.getUserService().saveFilmeLista(HomeActivity.IDUser, IDLista, fListaRequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    StyleableToast.makeText(ListasActivity.this, "O filme foi adicionado a lista", Toast.LENGTH_LONG, R.style.exampleToast).show();
                    }
                else{
                    StyleableToast.makeText(ListasActivity.this, "O filme falhou ao ser adicionado na lista", Toast.LENGTH_LONG, R.style.erroToast).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ListasActivity.this, "Salvamento falhou!" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}