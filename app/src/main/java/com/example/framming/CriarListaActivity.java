package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CriarListaActivity extends AppCompatActivity {

    Button btnCriarL;
    EditText etxtNomeLista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_lista);
        etxtNomeLista = findViewById(R.id.etxtnomelista);
        btnCriarL = findViewById(R.id.btnCriarL);

        btnCriarL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(etxtNomeLista.getText().toString().equals(null)){

                }
                else{
                    saveLista(createLista());
                }
            }
        });
    }

    public ListaRequest createLista(){
        ListaRequest listaRequest = new ListaRequest();
        listaRequest.setListDescription(etxtNomeLista.getText().toString());

        return listaRequest;
    }

    public void saveLista(ListaRequest listaRequest){
        Call<CriarListaResponse> result = ApiClient.getUserService().saveNewLista(HomeActivity.IDUser, listaRequest);
        result.enqueue(new Callback<CriarListaResponse>() {
            @Override
            public void onResponse(Call<CriarListaResponse> call, Response<CriarListaResponse> response) {
                if(response.isSuccessful()){
                    if(MainActivity.addLista == true){
                        saveFLista(response.body().getIdLista(), createFLista());
                    }
                    else{
                        StyleableToast.makeText(CriarListaActivity.this, "Sua lista foi criada com sucesso", Toast.LENGTH_LONG, R.style.exampleToast).show();
                        startActivity(new Intent(CriarListaActivity.this, ListasActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                    }
                }
                else{
                    StyleableToast.makeText(CriarListaActivity.this, "A criação falhou. Tente novamente.", Toast.LENGTH_LONG, R.style.erroToast).show();
                }
            }

            @Override
            public void onFailure(Call<CriarListaResponse> call, Throwable t) {
                Toast.makeText(CriarListaActivity.this, "Criação falhou!" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                    StyleableToast.makeText(CriarListaActivity.this, "Sua lista foi criada e o filme foi adicionado", Toast.LENGTH_LONG, R.style.exampleToast).show();
                    if(MainActivity.addLista){
                        startActivity(new Intent(CriarListaActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                    }
                    else{
                        startActivity(new Intent(CriarListaActivity.this, ListasActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                    }
                    }
                else{
                    StyleableToast.makeText(CriarListaActivity.this, "Criação falhou", Toast.LENGTH_LONG, R.style.erroToast).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(CriarListaActivity.this, "Criação falhou!" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}