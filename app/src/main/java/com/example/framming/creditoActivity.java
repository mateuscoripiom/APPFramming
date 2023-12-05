package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class creditoActivity extends AppCompatActivity {

    Button btnFinalizar;
    EditText etxtNumCard, etxtNameCard, etxtCpfUser, etxtCvvCard, etxtValCard;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credito);

        btnFinalizar = findViewById(R.id.btnFinalizar);
        etxtNumCard = findViewById(R.id.etxtnumcard);
        etxtNameCard = findViewById(R.id.etxtnometit);
        etxtCpfUser = findViewById(R.id.etxtcpftit);
        etxtCvvCard = findViewById(R.id.etxtcvv);
        etxtValCard = findViewById(R.id.etxtvalcard);
        radioButton = findViewById(R.id.radioButton);

        etxtCpfUser.setText(formadepagamentoActivity.CPFTitular);

        btnFinalizar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                savePayment(createPayment());


            }
        });


    }

    public PaymentRequest createPayment(){
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setNumCard(etxtNumCard.getText().toString());
        paymentRequest.setNameCard(etxtNameCard.getText().toString());
        paymentRequest.setCpfUser(etxtCpfUser.getText().toString());
        paymentRequest.setCvvCard(etxtCvvCard.getText().toString());
        paymentRequest.setValCard(etxtValCard.getText().toString());

        return paymentRequest;
    }

    public void savePayment(PaymentRequest paymentRequest){
        Call<ResponseBody> result = ApiClient.getUserService().savePayment(HomeActivity.IDUser, paymentRequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    StyleableToast.makeText(creditoActivity.this, "Sua compra foi realizada com sucesso", Toast.LENGTH_LONG, R.style.exampleToast).show();
                    saveTicket(createTicket());
                }
                else{
                    Toast.makeText(creditoActivity.this, "Sua compra falhou. Verifique seus dados.", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(creditoActivity.this, "Compra falhou!" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public TicketRequest createTicket(){
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setIdMovie(MainActivity.idfilmetiping);
        ticketRequest.setIdTicket(MyAdapterTipIng.tipoIngresso);
        ticketRequest.setNumTickets(String.valueOf(MyAdapterTipIng.numIngr));
        ticketRequest.setNumCardPayment(etxtNumCard.getText().toString());

        return ticketRequest;
    }

    public void saveTicket(TicketRequest ticketRequest){
        Call<ResponseBody> result = ApiClient.getUserService().saveTicket(HomeActivity.IDUser, ticketRequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    StyleableToast.makeText(creditoActivity.this, "Sua compra foi realizada com sucesso", Toast.LENGTH_LONG, R.style.exampleToast).show();
                    startActivity(new Intent(creditoActivity.this, CadIngressosActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                }
                else{
                    StyleableToast.makeText(creditoActivity.this, "Sua compra falhou. Verifique seus dados", Toast.LENGTH_LONG, R.style.erroToast).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(creditoActivity.this, "Compra falhou!" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}