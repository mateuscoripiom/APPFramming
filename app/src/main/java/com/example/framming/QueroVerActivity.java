package com.example.framming;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QueroVerActivity extends AppCompatActivity {

    public static ArrayList<ItemQueroVer> itemsquerover = new ArrayList<ItemQueroVer>();
    public static ArrayList<ItemQueroVerFinal> itemsqueroverfinal = new ArrayList<>();
    RecyclerView recyclerViewQueroVer;
    ImageView imgFundoQV;
    TextView txtqueroverc;
    public static int contagemquerover = 0;
    public static boolean usadoqv = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quero_ver);
        recyclerViewQueroVer = findViewById(R.id.recyclerViewQueroV);
        imgFundoQV = findViewById(R.id.imgQueroV);
        txtqueroverc = findViewById(R.id.txtqueroverc);

        buscarQueroVer();

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                contagemquerover = 0;
                itemsquerover.clear();
                itemsqueroverfinal.clear();
                HomeActivity.items.clear();
                HomeActivity.itemsfinal.clear();
                startActivity(new Intent(QueroVerActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
    public void buscarQueroVer(){
        Call<ArrayList<ItemQueroVer>> result = ApiClient.getUserService().getQueroVer(HomeActivity.IDUser);
        result.enqueue(new Callback<ArrayList<ItemQueroVer>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemQueroVer>> call, Response<ArrayList<ItemQueroVer>> response) {
                if(response.isSuccessful()){
                    itemsquerover = response.body();
                    Picasso
                            .get()
                            .load("https://www.themoviedb.org/t/p/original" + itemsquerover.get(0).getBackdrop_path())
                            .into(imgFundoQV);
                    int b=0;

                    if(response.body().toString().equals("Watch later movies not found")){
                        txtqueroverc.setText("Parece que ainda não tem nada marcado por aqui!");
                    }

                    for(b=0; b<itemsquerover.size(); b++){
                        contagemquerover++;
                        txtqueroverc.setText(contagemquerover + " filmes marcados para o futuro");

                        buscarPosterFav(itemsquerover.get(b).getId(), itemsquerover.get(b).getPoster_path(), itemsquerover.get(b).getBackdrop_path(), b);

                    }



                }
                else{
                    txtqueroverc.setText("Parece que ainda não tem nada \n marcado por aqui!");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemQueroVer>> call, Throwable t) {
                StyleableToast.makeText(QueroVerActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void buscarPosterFav(String filmeID, String linkPoster, String backdrop, Integer ordemArray) {
        Call<PosterResponse> result = ApiClient.getUserService().getAllPosters(HomeActivity.IDUser, filmeID);
        result.enqueue(new Callback<PosterResponse>() {
            @Override
            public void onResponse(Call<PosterResponse> call, Response<PosterResponse> response) {
                if (response.code() != 200) {
                    itemsqueroverfinal.add(new ItemQueroVerFinal(filmeID, linkPoster, backdrop, ordemArray));

                }
                if (response.isSuccessful()) {
                    itemsqueroverfinal.add(new ItemQueroVerFinal(filmeID, response.body().getLinkPoster(), backdrop, ordemArray));
                    //Log.v("Tag", "The title" + itemsfavfinal.get(1).getPosterFilme().toString());
                }
                sortListByArrayQV(itemsqueroverfinal);
                recyclerViewQueroVer.setLayoutManager(new GridLayoutManager(QueroVerActivity.this, 3));
                recyclerViewQueroVer.setAdapter(new MyAdapterQueroVer(getApplicationContext(),itemsqueroverfinal));


                recyclerViewQueroVer.addOnItemTouchListener(
                        new RecyclerItemClickListener(getApplicationContext(), recyclerViewQueroVer, new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                usadoqv = true;
                                HomeActivity.IDPositionPop = itemsqueroverfinal.get(position).getId();
                                startActivity(new Intent(QueroVerActivity.this, MainActivity.class));
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
                StyleableToast.makeText(QueroVerActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });


    }
    private void sortListByArrayQV(ArrayList<ItemQueroVerFinal> theArrayListEvents) {
        Collections.sort(theArrayListEvents, new EventDetailSortByArrayQV());
    }
}