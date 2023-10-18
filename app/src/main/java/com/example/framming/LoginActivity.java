package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.metrics.LogSessionId;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editTextemail, editTextsenha;
    Button btnlogin;
    TextView txtcadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextemail = findViewById(R.id.editTextTextPersonName);
        editTextsenha = findViewById(R.id.editTextTextPassword);
        btnlogin = findViewById(R.id.button3);
        txtcadastrar = findViewById(R.id.textView2);

        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                logarUsuario(createRequest());
            }
        });

        txtcadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });

    }

    public LoginRequest createRequest(){
        LoginRequest loginRequest = new LoginRequest();
        if(editTextemail.getText().toString() != "" || editTextsenha.getText().toString() != ""){
            loginRequest.setEmail(editTextemail.getText().toString());
            loginRequest.setSenha(editTextsenha.getText().toString());
        }
        else{
            Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }


        return loginRequest;
    }

    public void logarUsuario(LoginRequest loginRequest){
        Call<ResponseBody> result = ApiClient.getUserService().logarUsuario(loginRequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "Login falhou", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login falhou " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}