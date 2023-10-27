package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class RecompensasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_recompensas);
    }
}