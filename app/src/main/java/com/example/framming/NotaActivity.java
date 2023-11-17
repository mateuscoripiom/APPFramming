package com.example.framming;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotaActivity extends AppCompatActivity {

    RatingBar ratingBarNota;
    TextView txtFilmeNota;
    Button btnsalvarNota;

    ImageView imgPosterNota, imgFundoNota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota);

        ratingBarNota = findViewById(R.id.ratingBar4);
        txtFilmeNota = findViewById(R.id.txtName4);
        btnsalvarNota = findViewById(R.id.btnSalvarNota);
        imgPosterNota = findViewById(R.id.imgPosterNota);
        imgFundoNota = findViewById(R.id.imgFundo16);

        txtFilmeNota.setText(MainActivity.nomeFilmeTipIng);
        Picasso
                .get()
                .load("https://www.themoviedb.org/t/p/original" + MainActivity.posterFilmeTipIng)
                .into(imgPosterNota);
        Picasso
                .get()
                .load("https://www.themoviedb.org/t/p/original" + MainActivity.fundoFilmeTipIng)
                .into(imgFundoNota);

        btnsalvarNota.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                salvarCriticaNota(feedbackRequest());
            }
        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String makeDateString(int day, int month, int year) {
        return  day + "/" + month + "/" + year;
    }

    public FeedbackRequest feedbackRequest(){
        FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setIdMovie(MainActivity.IDFilme);
        feedbackRequest.setFeedbackText("");
        feedbackRequest.setFeedbackDate(getTodaysDate().toString());
        feedbackRequest.setFeedbackRate(ratingBarNota.getRating());
        return feedbackRequest;
    }

    public void salvarCriticaNota(FeedbackRequest feedbackRequest){
        Call<ResponseBody> result = ApiClient.getUserService().saveFeedback(HomeActivity.IDUser, feedbackRequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    StyleableToast.makeText(NotaActivity.this, "Sua nota de " + MainActivity.nomeFilmeTipIng + " foi salva", Toast.LENGTH_LONG, R.style.exampleToast).show();

                }
                else{
                    StyleableToast.makeText(NotaActivity.this, "Não foi possível salvar sua crítica", Toast.LENGTH_LONG, R.style.erroToast).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(NotaActivity.this, "Salvamento falhou" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}