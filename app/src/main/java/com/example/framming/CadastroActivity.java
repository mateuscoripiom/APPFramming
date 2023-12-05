package com.example.framming;

import static android.opengl.ETC1.encodeImage;

import static com.example.framming.LoginActivity.KEY_EMAIL;
import static com.example.framming.LoginActivity.KEY_ID;
import static com.example.framming.LoginActivity.SHARED_PREFS;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    EditText editTextname, editTextuser, editTextemail, editTextconfirmasenha;

    TextInputEditText editTextsenha;

    Button btncadastrar, btnfacalogin;
    ImageView imgEscolherImagem;

    public static String nome, user, email, senha, confirmasenha, base64, imgprofile;
    public static Uri imgprofiled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_cadastro);

        checkBox();

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

    private void checkBox() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String check = sharedPreferences.getString(KEY_EMAIL, null);
        String idu = sharedPreferences.getString(KEY_ID, null);
        if(check != null && idu != null){
            HomeActivity.IDUser = sharedPreferences.getString(KEY_ID, "");
            startActivity(new Intent(CadastroActivity.this, HomeActivity.class));

        }
    }

    public UsuarioRequest createRequest(){

        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setName(editTextname.getText().toString());
        usuarioRequest.setUsername(editTextuser.getText().toString());
        usuarioRequest.setEmail(editTextemail.getText().toString());
        usuarioRequest.setPassword(editTextsenha.getText().toString());
        usuarioRequest.setIcon("https://i.imgur.com/b3vtOtU.png");
        usuarioRequest.setUserType("nor");

        return usuarioRequest;
    }

    public void salvarUsuario(UsuarioRequest usuarioRequest){
        Call<CadastroReponse> result = ApiClient.getUserService().salvarUsuario(usuarioRequest);
        result.enqueue(new Callback<CadastroReponse>() {
            @Override
            public void onResponse(Call<CadastroReponse> call, Response<CadastroReponse> response) {
                if(response.isSuccessful()){
                    SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(KEY_EMAIL, editTextemail.getText().toString());
                    editor.putString(KEY_ID, response.body().getIdUsuario());
                    editor.apply();

                    StyleableToast.makeText(CadastroActivity.this, "Cadastrado com sucesso!", Toast.LENGTH_LONG, R.style.exampleToast).show();
                    startActivity(new Intent(CadastroActivity.this, HomeActivity.class));
                    HomeActivity.IDUser = response.body().getIdUsuario();
                }
                else{
                    //Toast.makeText(CadastroActivity.this, "Verifique todos os campos e tente novamente", Toast.LENGTH_SHORT).show();
                    StyleableToast.makeText(CadastroActivity.this, "Verifique todos os campos e tente novamente", Toast.LENGTH_LONG, R.style.exampleToast).show();

                }
            }

            @Override
            public void onFailure(Call<CadastroReponse> call, Throwable t) {
                Toast.makeText(CadastroActivity.this, "Salvamento falhou" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}