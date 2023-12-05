package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class formadepagamentoActivity extends AppCompatActivity {
    TextView totalPag;
    Button btnContinuar;
    EditText etxtcpftitular;

    public static String CPFTitular;
    public static boolean pagamentoSalvo = false;

    public static ArrayList<PaymentResponse> itemspagamento = new ArrayList<PaymentResponse>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formadepagamento);

        totalPag = findViewById(R.id.totalPag);
        etxtcpftitular = findViewById(R.id.etxtcpftitular);
        btnContinuar = findViewById(R.id.btnContinuar);

        btnContinuar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                buscarPagamentos();


            }
        });


        totalPag.setText("Seu total a pagar é de: R$ " + MyAdapterTipIng.valorFIng);
    }

    public void buscarPagamentos() {
        Call<ArrayList<PaymentResponse>> result = ApiClient.getUserService().getUserPaymets(etxtcpftitular.getText().toString());
        result.enqueue(new Callback<ArrayList<PaymentResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<PaymentResponse>> call, Response<ArrayList<PaymentResponse>> response) {
                if (response.isSuccessful()) {
                    itemspagamento = response.body();

                    int b = 0;

                    if(itemspagamento.toString().equals("Payment methods not found")){
                        CPFTitular = etxtcpftitular.getText().toString();
                        startActivity(new Intent(formadepagamentoActivity.this, creditoActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }
                    else{
                        pagamentoSalvo = true;
                        CPFTitular = etxtcpftitular.getText().toString();
                        startActivity(new Intent(formadepagamentoActivity.this, CartoesSalvosActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
                    }

                } else {

                }
            }

            @Override
            public void onFailure(Call<ArrayList<PaymentResponse>> call, Throwable t) {
                StyleableToast.makeText(formadepagamentoActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }
}