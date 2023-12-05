package com.example.framming;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadIngressosActivity extends AppCompatActivity {

    RecyclerView recyclerViewCadIng;
    public static ArrayList<TicketResponse> tickets = new ArrayList<>();
    public static ArrayList<TicketsFinais> ticketsfinais = new ArrayList<>();
    public static ArrayList<ItemFilme> itemsfilme = new ArrayList<>();
    public static ArrayList<ItemSessionTipIng> itemssession = new ArrayList<>();
    public static ArrayList<TicketsFinaisS> ticketsFinaisSES = new ArrayList<>();
    public static ArrayList<TicketsFinaisC> ticketsFinaisCS = new ArrayList<>();
    public static ArrayList<TicketsFinaisC> ticketsexibido = new ArrayList<>();

    TextView txtErroCadIng;
    ImageButton imgbtnvoltar22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_ingressos);
        recyclerViewCadIng = findViewById(R.id.recyclerViewCadIng);
        txtErroCadIng = findViewById(R.id.txtErroCadIng);
        imgbtnvoltar22 = findViewById(R.id.imgbtnvoltar22);

        buscarIngressos();

        imgbtnvoltar22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                tickets.clear();
                ticketsexibido.clear();
                ticketsfinais.clear();
                ticketsFinaisCS.clear();
                ticketsFinaisSES.clear();
                itemssession.clear();
                itemsfilme.clear();
                startActivity(new Intent(CadIngressosActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                tickets.clear();
                ticketsexibido.clear();
                ticketsfinais.clear();
                ticketsFinaisCS.clear();
                ticketsFinaisSES.clear();
                itemssession.clear();
                itemsfilme.clear();
                startActivity(new Intent(CadIngressosActivity.this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public void buscarIngressos(){
        Call<ArrayList<TicketResponse>> result = ApiClient.getUserService().getAllUsersTicker(HomeActivity.IDUser);
        result.enqueue(new Callback<ArrayList<TicketResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TicketResponse>> call, Response<ArrayList<TicketResponse>> response) {
                if(response.isSuccessful()){
                    tickets = response.body();

                    if(tickets.toString().equals("[]")){
                        txtErroCadIng.setVisibility(View.VISIBLE);
                        txtErroCadIng.setText("Parece que você ainda não \ncomprou nenhum ingresso");
                        recyclerViewCadIng.setVisibility(View.GONE);
                    }
                    else {
                        int b = 0;
                        for (b = 0; b < tickets.size(); b++) {
                            buscarSessaoID(tickets.get(b).getIdSessao(), tickets.get(b).getQtdTickets());
                        }
                    }


                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<TicketResponse>> call, Throwable t) {
                StyleableToast.makeText(CadIngressosActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public void buscarSessaoID(String sessaoID, int qtdIngressos){
        Call<ArrayList<ItemSessionTipIng>> result = ApiClient.getUserService().getSessionID(sessaoID);
        result.enqueue(new Callback<ArrayList<ItemSessionTipIng>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemSessionTipIng>> call, Response<ArrayList<ItemSessionTipIng>> response) {
                if(response.isSuccessful()){
                    itemssession = response.body();
                    ticketsfinais.add(new TicketsFinais(itemssession.get(0).getSalaSessao(),sessaoID, qtdIngressos, itemssession.get(0).getTokenCinema(), itemssession.get(0).getIdFilme(), itemssession.get(0).getDataSessao(), itemssession.get(0).getHorarioSessao()));

                    int b=0;
                    for (b = 0; b < ticketsfinais.size(); b++) {
                        buscarFilmeAPI(ticketsfinais.get(b).getSalaSessao(), ticketsfinais.get(b).getIdFilme(), ticketsfinais.get(b).getSessaoId(), ticketsfinais.get(b).getQtdIngressos(), ticketsfinais.get(b).getTokenCinema(), ticketsfinais.get(b).getDataSessao(), ticketsfinais.get(b).getHorarioSessao());
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemSessionTipIng>> call, Throwable t) {
                StyleableToast.makeText(CadIngressosActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }


    public void buscarFilmeAPI(String salaSessao, String filmeID, String idSessao, int qtdIngressos, String tokenCinema, String dataSessao, String horarioSessao){
        Call<ArrayList<ItemFilme>> result = ApiClient.getUserService().getAllDataFilmeAPI(filmeID);
        result.enqueue(new Callback<ArrayList<ItemFilme>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemFilme>> call, Response<ArrayList<ItemFilme>> response) {
                if(response.isSuccessful()){

                    itemsfilme = response.body();
                        ticketsFinaisSES.add(new TicketsFinaisS(itemsfilme.get(0).getPoster_path(), salaSessao, filmeID, idSessao, qtdIngressos, tokenCinema, dataSessao, horarioSessao, itemsfilme.get(0).getTitle(), itemsfilme.get(0).getBackdrop_path()));
                    int b=0;

                    for(b=0; b<ticketsFinaisSES.size(); b++){
                        buscarCinema(ticketsFinaisSES.get(b).getPosterFilme(), ticketsFinaisSES.get(b).getSalaSessao(), ticketsFinaisSES.get(b).getIdFilme(), ticketsFinaisSES.get(b).getIdSessao(), ticketsFinaisSES.get(b).getQtdIngressos(), ticketsFinaisSES.get(b).getTokenCinema(), ticketsFinaisSES.get(b).getDataSessao(), ticketsFinaisSES.get(b).getHorarioSessao(),ticketsFinaisSES.get(b).getNomeFilme(), ticketsFinaisSES.get(b).getBackdropFilme());
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemFilme>> call, Throwable t) {
                StyleableToast.makeText(CadIngressosActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }


    public void buscarCinema(String posterFilme, String salaSessao, String filmeID, String idSessao, int qtdIngressos, String tokenCinema, String dataSessao, String horarioSessao, String nomeFilme, String backdropFilme){
        Call<CinemaResponse> result = ApiClient.getUserService().getCinemaData(tokenCinema);
        result.enqueue(new Callback<CinemaResponse>() {
            @Override
            public void onResponse(Call<CinemaResponse> call, Response<CinemaResponse> response) {
                if(response.isSuccessful()){
                    ticketsFinaisCS.add(new TicketsFinaisC(posterFilme, salaSessao, filmeID, idSessao, qtdIngressos, tokenCinema, dataSessao, horarioSessao, nomeFilme, backdropFilme, response.body().getNomeCinema()));
                    int b=0;

                    for (int i = 0; i < ticketsFinaisCS.size(); i++) {
                        MyAdapterCadIng myAdapterCadIng = new MyAdapterCadIng(CadIngressosActivity.this, ticketsFinaisCS);
                        LinearLayoutManager manager = new LinearLayoutManager(CadIngressosActivity.this, recyclerViewCadIng.VERTICAL, false);
                        recyclerViewCadIng.setLayoutManager(manager);
                        recyclerViewCadIng.setAdapter(myAdapterCadIng);

                        recyclerViewCadIng.addOnItemTouchListener(
                                new RecyclerItemClickListener(getApplicationContext(), recyclerViewCadIng, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int position) {
                                        ticketsFinaisCS.get(position);
                                        int b = 0;

                                        ticketsexibido.add(new TicketsFinaisC(ticketsFinaisCS.get(b).getPosterFilme(), ticketsFinaisCS.get(b).getSalaSessao(), ticketsFinaisCS.get(b).getIdFilme(), ticketsFinaisCS.get(b).getIdSessao(), ticketsFinaisCS.get(b).getQtdIngressos(), ticketsFinaisCS.get(b).getTokenCinema(), ticketsFinaisCS.get(b).getDataSessao(), ticketsFinaisCS.get(b).getHorarioSessao(), ticketsFinaisCS.get(b).getNomeFilme(), ticketsFinaisCS.get(b).getBackdropFilme(), ticketsFinaisCS.get(b).getNomeCinema()));

                                        startActivity(new Intent(CadIngressosActivity.this, ExibeIngressoActivity.class));
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
            public void onFailure(Call<CinemaResponse> call, Throwable t) {
                StyleableToast.makeText(CadIngressosActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }
}