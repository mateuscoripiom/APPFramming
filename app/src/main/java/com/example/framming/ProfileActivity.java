package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    CardView cardPontos, cardDiario;
    TextView txtNomeUsuario, txtUserName;
    ImageView imgIconUsuario;
    Button btntipouser;

    Toolbar toolbarprofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        cardPontos = findViewById(R.id.cardPontos);
        txtNomeUsuario = findViewById(R.id.txtNomeUsuario);
        txtUserName = findViewById(R.id.txtUserName);
        imgIconUsuario = findViewById(R.id.imgIconUsuario);
        btntipouser = findViewById(R.id.btntipouser);
        cardDiario = findViewById(R.id.cardDiario);
        toolbarprofile = findViewById(R.id.toolbar3);

        txtNomeUsuario.setText(HomeActivity.nomeusuario);
        txtUserName.setText("@" + HomeActivity.nickusuario);

        toolbarprofile.inflateMenu(R.menu.profile_menu);
        toolbarprofile.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.nav_editarusu) {
                    startActivity(new Intent(ProfileActivity.this, PosterActivity.class));

                    finish();
                } else if (item.getItemId() == R.id.nav_editarfav) {

                }

                return false;
            }
        });

        if(HomeActivity.iconusuario.equals("https://imageupload.io/ib/yzauelSzIISZpoC_1697494770.png")){
            Picasso
                    .get()
                    .load(HomeActivity.iconusuario)
                    .into(imgIconUsuario);
        }
        else{
            imgIconUsuario.setImageURI(Uri.parse(HomeActivity.iconusuario));
        }



        if(HomeActivity.tipoperfil.equals("nor")){
            btntipouser.setText("USUÁRIO PIPOCA");
            btntipouser.setBackgroundColor(Color.GRAY);
            btntipouser.setTextColor(Color.WHITE);
        }

        cardPontos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ProfileActivity.this, RecompensasActivity.class));
            }
        });

        cardDiario.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ProfileActivity.this, DiaryActivity.class));
            }
        });

    }
}