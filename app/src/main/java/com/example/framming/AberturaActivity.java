package com.example.framming;

import static com.example.framming.LoginActivity.KEY_EMAIL;
import static com.example.framming.LoginActivity.KEY_ID;
import static com.example.framming.LoginActivity.SHARED_PREFS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import io.github.muddz.styleabletoast.StyleableToast;

public class AberturaActivity extends AppCompatActivity {
    private Button btnLogin, btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abertura);

        checkBox();

        btnLogin = findViewById(R.id.btnLogin);
        btnCadastro = findViewById(R.id.btnCadastro);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AberturaActivity.this, HomeActivity.class));
                finish();
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(AberturaActivity.this, CadastroActivity.class));
                finish();
            }
        });
    }

    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString(KEY_EMAIL, null);
        String idu = sharedPreferences.getString(KEY_ID, null);
        if(check != null && idu != null){
            HomeActivity.IDUser = sharedPreferences.getString(KEY_ID, "");
            startActivity(new Intent(AberturaActivity.this, HomeActivity.class));

        }
    }
}