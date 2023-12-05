package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfirmaNumCard extends AppCompatActivity {

    EditText etxtconfnumcard;
    Button btnConfirmaCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirma_num_card);

        etxtconfnumcard = findViewById(R.id.etxtconfnumcard);
        btnConfirmaCard = findViewById(R.id.btnContinuar2);

        btnConfirmaCard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(etxtconfnumcard.getText().toString().equals(CartoesSalvosActivity.numCartao)) {
                    saveTicket(createTicket(etxtconfnumcard.getText().toString()));
                }
                else{
                    StyleableToast.makeText(ConfirmaNumCard.this, "Os números não conferem", Toast.LENGTH_LONG, R.style.erroToast).show();

                }
            }
        });

    }

    public TicketRequest createTicket(String numCard){
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setIdMovie(MainActivity.idfilmetiping);
        ticketRequest.setIdTicket(MyAdapterTipIng.tipoIngresso);
        ticketRequest.setNumTickets(String.valueOf(MyAdapterTipIng.numIngr));
        ticketRequest.setNumCardPayment(numCard);

        return ticketRequest;
    }

    public void saveTicket(TicketRequest ticketRequest){
        Call<ResponseBody> result = ApiClient.getUserService().saveTicket(HomeActivity.IDUser, ticketRequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    StyleableToast.makeText(ConfirmaNumCard.this, "Sua compra foi realizada com sucesso", Toast.LENGTH_LONG, R.style.exampleToast).show();
                    startActivity(new Intent(ConfirmaNumCard.this, CadIngressosActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                }
                else{
                    StyleableToast.makeText(ConfirmaNumCard.this, "Sua compra falhou. Verifique seus dados", Toast.LENGTH_LONG, R.style.erroToast).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ConfirmaNumCard.this, "Compra falhou!" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}