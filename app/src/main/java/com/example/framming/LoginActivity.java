package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.metrics.LogSessionId;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editTextemail, editTextsenha;
    Button btnlogin;
    TextView txtcadastrar;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_login);

        checkBox();

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
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
            }
        });

    }

    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString(KEY_EMAIL, null);
        String idu = sharedPreferences.getString(KEY_ID, null);
        if(check != null && idu != null){
            HomeActivity.IDUser = sharedPreferences.getString(KEY_ID, "");
            StyleableToast.makeText(LoginActivity.this, "Logado com sucesso!", Toast.LENGTH_LONG, R.style.exampleToast).show();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));

        }
    }

    public LoginRequest createRequest(){
        LoginRequest loginRequest = new LoginRequest();
        if(editTextemail.getText().toString() != "" || editTextsenha.getText().toString() != ""){
            loginRequest.setEmail(editTextemail.getText().toString());
            loginRequest.setPassword(editTextsenha.getText().toString());
        }
        else{
            Toast.makeText(LoginActivity.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }


        return loginRequest;
    }

    public void logarUsuario(LoginRequest loginRequest){
        Call<LoginResult> result = ApiClient.getUserService().logarUsuario(loginRequest);
        result.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                if(response.isSuccessful()){
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(KEY_EMAIL, editTextemail.getText().toString());
                    editor.putString(KEY_ID, response.body().getIdUsuario());
                    editor.apply();

                    HomeActivity.IDUser = response.body().getIdUsuario();
                    StyleableToast.makeText(LoginActivity.this, "Logado com sucesso!", Toast.LENGTH_LONG, R.style.exampleToast).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));



                    //Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();

                }
                else{
                    StyleableToast.makeText(LoginActivity.this, "Verifique seus dados e tente novamente!", Toast.LENGTH_LONG, R.style.erroToast).show();

                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                StyleableToast.makeText(LoginActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }
}