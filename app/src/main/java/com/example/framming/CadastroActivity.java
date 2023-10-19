package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    EditText editTextname, editTextuser, editTextemail, editTextconfirmasenha;

    TextInputEditText editTextsenha;

    Button btncadastrar, btnfacalogin;

    public static String nome, user, email, senha, confirmasenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        editTextname = findViewById(R.id.editTextTextPersonName);
        editTextuser = findViewById(R.id.editTextusuario);
        editTextemail = findViewById(R.id.editTextemail);
        editTextsenha = findViewById(R.id.editTextsenha);
        editTextconfirmasenha = findViewById(R.id.editTextconfirme);
        btncadastrar = findViewById(R.id.btn_cad);
        btnfacalogin = findViewById(R.id.facalogin);

        btncadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                nome = editTextname.getText().toString();
                user = editTextuser.getText().toString();
                email = editTextemail.getText().toString();
                senha = editTextsenha.getText().toString();
                confirmasenha = editTextconfirmasenha.getText().toString();

                 if(senha.equals(confirmasenha)){
                     salvarUsuario(createRequest());
                 }
                 else{
                     Toast.makeText(CadastroActivity.this, "As senhas não coincidem", Toast.LENGTH_SHORT).show();
                 }
            }
        });

        btnfacalogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(CadastroActivity.this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));;
            }
        });

    }

    public UsuarioRequest createRequest(){
        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setName(editTextname.getText().toString());
        usuarioRequest.setUsername(editTextuser.getText().toString());
        usuarioRequest.setEmail(editTextemail.getText().toString());
        usuarioRequest.setPassword(editTextsenha.getText().toString());
        usuarioRequest.setUserType("nor");

        return usuarioRequest;
    }

    public void salvarUsuario(UsuarioRequest usuarioRequest){
        Call<ResponseBody> result = ApiClient.getUserService().salvarUsuario(usuarioRequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CadastroActivity.this, HomeActivity.class));
                }
                else{
                    //Toast.makeText(CadastroActivity.this, "Verifique todos os campos e tente novamente", Toast.LENGTH_SHORT).show();
                    StyleableToast.makeText(CadastroActivity.this, "Verifique todos os campos e tente novamente", Toast.LENGTH_LONG, R.style.exampleToast).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(CadastroActivity.this, "Salvamento falhou" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}