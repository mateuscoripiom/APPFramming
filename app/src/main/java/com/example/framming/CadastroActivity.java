package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    EditText editTextname, editTextuser, editTextemail, editTextsenha, editTextconfirmasenha;

    Button btncadastrar;

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

        btncadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                salvarUsuario(createRequest());
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
                }
                else{
                    Toast.makeText(CadastroActivity.this, "Salvamento falhou", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(CadastroActivity.this, "Salvamento falhou" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}