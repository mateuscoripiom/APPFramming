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
import android.widget.EditText;
import android.widget.Toast;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditarUsuActivity extends AppCompatActivity {

    EditText editarName, editarUser, editarEmail, editarSenha;
    Button confAlterar, confExcluir;

    public static String emailUsu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usu);
        editarName = findViewById(R.id.etxtnameedit);
        editarUser = findViewById(R.id.etxtusernameedit);
        editarEmail = findViewById(R.id.etxtemailedit);
        editarSenha = findViewById(R.id.etxtsenhaedit);
        confAlterar = findViewById(R.id.btnFinalizar2);
        confExcluir = findViewById(R.id.btnFinalizar3);

        buscaInfoUser();

        editarEmail.setEnabled(false);
        editarEmail.setClickable(false);

        confAlterar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                salvarEdicoes(createERequest());
            }
        });

        confExcluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                deletarUsu();
            }
        });

    }

    public void buscaInfoUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://framming-api.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService userService = retrofit.create(UserService.class);

        Call<UserResponse> call = userService.getAllDataUser(HomeActivity.IDUser);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() != 200) {
                    //Toast.makeText(MainActivity.this, "Cheque sua conexão", Toast.LENGTH_SHORT).show();

                }
                if (response.isSuccessful()) {
                    String nomeusu = "";
                    String username = "";
                    String email = "";
                    String senha = "";

                    nomeusu = response.body().getNomeUsuario();
                    username = response.body().getNickUsuario();
                    email = response.body().getEmailUsuario();
                    senha = response.body().getSenhaUsuario();

                    emailUsu = response.body().getEmailUsuario();

                    if (nomeusu != null || nomeusu != "") {
                        editarEmail.setHint("Email atual: " + email);
                        editarName.setHint("Nome atual: " + nomeusu);
                        editarUser.setHint("User atual: " + "@" + username);
                        editarSenha.setHint("Editar senha");
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                startActivity(new Intent(EditarUsuActivity.this, ErroAPIActivity.class));
            }
        });
    }

    public EditarRequest createERequest(){

        EditarRequest editarRequest = new EditarRequest();
        editarRequest.setName(editarName.getText().toString());
        editarRequest.setUsername(editarUser.getText().toString());
        editarRequest.setEmail(emailUsu);
        editarRequest.setPassword(editarSenha.getText().toString());

        return editarRequest;
    }

    public void salvarEdicoes(EditarRequest editarRequest){
        Call<ResponseBody> result = ApiClient.getUserService().saveEditU(HomeActivity.IDUser, editarRequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    StyleableToast.makeText(EditarUsuActivity.this, "Edição feita com sucesso!", Toast.LENGTH_LONG, R.style.exampleToast).show();
                    startActivity(new Intent(EditarUsuActivity.this, ProfileActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                }
                else{
                    //Toast.makeText(CadastroActivity.this, "Verifique todos os campos e tente novamente", Toast.LENGTH_SHORT).show();
                    StyleableToast.makeText(EditarUsuActivity.this, "Verifique todos os campos e tente novamente", Toast.LENGTH_LONG, R.style.erroToast).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                StyleableToast.makeText(EditarUsuActivity.this, "Edição falhou", Toast.LENGTH_LONG, R.style.erroToast).show();

            }
        });
    }

    public void deletarUsu(){
        Call<ResponseBody> result = ApiClient.getUserService().deleteUser(HomeActivity.IDUser);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    StyleableToast.makeText(EditarUsuActivity.this, "Usuário deletado com sucesso!", Toast.LENGTH_LONG, R.style.exampleToast).show();
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    String check = sharedPreferences.getString(KEY_EMAIL, null);
                    String idu = sharedPreferences.getString(KEY_ID, null);
                    if (check != null && idu != null) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                    }
                    startActivity(new Intent(EditarUsuActivity.this, AberturaActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));

                }
                else{
                    //Toast.makeText(CadastroActivity.this, "Verifique todos os campos e tente novamente", Toast.LENGTH_SHORT).show();
                    StyleableToast.makeText(EditarUsuActivity.this, "Operação falhou", Toast.LENGTH_LONG, R.style.erroToast).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                StyleableToast.makeText(EditarUsuActivity.this, "Operação falhou", Toast.LENGTH_LONG, R.style.erroToast).show();

            }
        });
    }
}