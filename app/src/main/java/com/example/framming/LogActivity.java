package com.example.framming;

import static com.example.framming.MainActivity.usado;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private Button btndata;
    private DatePickerDialog datePickerDialog;
    private ImageView imgPosterCritica;
    private TextView txtName2, txtAno2, txtTagline;
    private ImageButton imgbtnvoltar3;

    public static ArrayList<ItemFilme> itemsfilme = new ArrayList<>();

    public static String critica;
    public static Float nota;
    public static String dataAssistido;
    public static String dataparasalvar;
    public static String nomeFilmeAssitido;
    public static String imgPosterAssistido;
    public static String anoFilmeAssistido;
    public static String imgFundoAssistido;
    private Button btnsalvar;
    private TextInputEditText textInputEditText;
    public static RatingBar ratingBar;
    public static String idfilmeassistido;
    public static boolean logusado = false;

    TextView txtNomeUsuario, txtUserName;
    ImageView imgIconUsuario;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        initDatePicker();
        btndata = findViewById(R.id.btndata);
        imgPosterCritica = findViewById(R.id.imgPosterCritica);
        txtName2 = findViewById(R.id.txtName2);
        txtAno2 = findViewById(R.id.txtAno2);
        txtTagline = findViewById(R.id.txtTagline);
        btndata.setText(getTodaysDate());
        imgbtnvoltar3 = findViewById(R.id.imgbtnvoltar3);
        btnsalvar = findViewById(R.id.btnSalvar);
        textInputEditText = findViewById(R.id.textInputEditText);
        ratingBar = findViewById(R.id.ratingBar);
        imgbtnvoltar3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(LogActivity.this, MainActivity.class));
            }
        });

        if(CriticaActivity.usoEdicao == true){
            idfilmeassistido = DiaryActivity.idFilmeMain;
            buscarFilmeAPI(idfilmeassistido);
            textInputEditText.setText(DiaryActivity.textoCritica);
            btndata.setText(DiaryActivity.dataCritica);
            ratingBar.setRating(DiaryActivity.notaCritica);

            btnsalvar.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    logusado = true;
                    salvarCriticaEditada(editedRequest());
                    CriticaActivity.usoEdicao = false;
                    startActivity(new Intent(LogActivity.this, DiaryActivity.class));
                    finish();
                }
            });
        }
        else{
            if(HomeActivity.ID != null) {
                idfilmeassistido = HomeActivity.ID;
            }
            if(HomeActivity.IDPositionPop != null){
                idfilmeassistido = HomeActivity.IDPositionPop;
            }
            if(PesquisaActivity.IDpesquisa != null){
                idfilmeassistido = PesquisaActivity.IDpesquisa;
            }

            buscarFilmeAPI(idfilmeassistido);
            OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
                @Override
                public void handleOnBackPressed() {
                    startActivity(new Intent(LogActivity.this, MainActivity.class));
                }
            };
            getOnBackPressedDispatcher().addCallback(this, callback);

            //buscaInfoFilme();

            btnsalvar.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    critica = textInputEditText.getText().toString();
                    logusado = true;
                    salvarCritica(feedbackRequest());
                    startActivity(new Intent(LogActivity.this, CriticaActivity.class));
                    finish();
                }
            });
        }

    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int finalday;
        if(day<10){
            finalday = 0 + day;
        }
        else{
            finalday = day;
        }

        return makeDateString(finalday, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                int finalday;
                if(day<10){
                    finalday = 0 + day;
                }
                else{
                    finalday = day;
                }
                String date = makeDateString(finalday, month, year);
                btndata.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int finalday;
        if(day<10){
            finalday = 0 + day;
        }
        else{
            finalday = day;
        }

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, finalday);
    }

    private String makeDateString(int finalday, int month, int year) {
        String datat;
        if(finalday<10){
            datat = "0" + finalday;
        }
        else{
            datat = String.valueOf(finalday);
        }
        dataAssistido = year + "-" + month + "-" + datat;
        dataparasalvar = year + "-" + month + "-" + datat;
        return  datat + " " + getMonthFormat(month) + ", " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "janeiro";
        if(month == 2)
            return "fevereiro";
        if(month == 3)
            return "março";
        if(month == 4)
            return "abril";
        if(month == 5)
            return "maio";
        if(month == 6)
            return "junho";
        if(month == 7)
            return "julho";
        if(month == 8)
            return "agosto";
        if(month == 9)
            return "setembro";
        if(month == 10)
            return "outubro";
        if(month == 11)
            return "novembro";
        if(month == 12)
            return "dezembro";

        return "janeiro";
    }

    public void openDatePicker(View view){
        datePickerDialog.show();
    }

    public void buscaInfoFilme() {
        // Recupera a string de busca.
        String movieString = null;
        if(HomeActivity.ID != null) {
            movieString = HomeActivity.ID;
            idfilmeassistido = HomeActivity.ID;
        }
        if(HomeActivity.IDPositionPop != null){
            movieString = HomeActivity.IDPositionPop;
            idfilmeassistido = HomeActivity.IDPositionPop;
        }
        if(PesquisaActivity.IDpesquisa != null){
            movieString = PesquisaActivity.IDpesquisa;
            idfilmeassistido = PesquisaActivity.IDpesquisa;
        }
        // esconde o teclado qdo o botão é clicado
        /*InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        } */

        // Verifica o status da conexão de rede
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        /* Se a rede estiver disponivel e o campo de busca não estiver vazio
         iniciar o Loader CarregaLivros */
        if (networkInfo != null && networkInfo.isConnected()
                && movieString.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("movieString", movieString);
            getSupportLoaderManager().restartLoader(0, queryBundle, this);
        }
        // atualiza a textview para informar que não há conexão ou termo de busca
        else {
            if (movieString.length() == 0) {
                Toast.makeText(LogActivity.this, "Termo inválido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LogActivity.this, "Verifique a conexão", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String movieString = "";
        if (args != null) {
            movieString = args.getString("movieString");
        }
        return new CarregaFilmeIDFramming(this, movieString);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            // Converte a resposta em Json
            JSONObject jsonObject = new JSONObject(data);
            // Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
            // Obtem o JSONArray dos itens de livros
            // JSONArray itemsArray = jsonObject.getJSONArray("genres");
            // Toast.makeText(this, itemsArray.toString(), Toast.LENGTH_SHORT).show();
            // inicializa o contador
            int i = 0;
            String nome = null;
            String imgposter = null;
            String imgfundo = null;
            String duracao = null;
            String ano = null;
            String line = null;
            // Procura pro resultados nos itens do array
            while (i < jsonObject.length() &&
                    (nome == null) && (imgposter == null) && (duracao == null) && (ano == null) && (line == null) && (imgfundo == null)) {
                // Obtem a informação
                Object title = jsonObject.get("title"); // pega o title no object json
                Object poster = jsonObject.get("poster_path");
                Object runtime = jsonObject.get("runtime");
                Object year = jsonObject.get("release_date");
                Object tagline = jsonObject.get("tagline");
                Object background = jsonObject.get("backdrop_path");

                // Toast.makeText(this, "MOVIE:" + title, Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    nome = title.toString();
                    imgposter = poster.toString();
                    duracao = runtime.toString();
                    ano = year.toString();
                    line = tagline.toString();
                    imgfundo = background.toString();
                    // Toast.makeText(this, "NOME:" + nome, Toast.LENGTH_SHORT).show();
                } catch (Exception err) {
                    err.printStackTrace();
                }
                // move para a proxima linha
                i++;
            }

            JSONArray itemsArray = jsonObject.getJSONArray("genres");
            int z = 0;
            String ngenero = null;
            while (z < itemsArray.length() &&
                    (ngenero == null)) {
                // Obtem a informação
                JSONObject genre = itemsArray.getJSONObject(z);
                // JSONObject volumeInfo = genre.getJSONObject("name");
                //Toast.makeText(this, genre.toString(), Toast.LENGTH_SHORT).show();
                //  Obter autor e titulo para o item,
                // erro se o campo estiver vazio
                try {
                    ngenero = genre.getString("name");
                    //Toast.makeText(this, ngenero.toString(), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // move para a proxima linha
                z++;
            }
            //mostra o resultado qdo possivel.
            if ((nome != null)  && (imgposter != null) && (duracao != null) && (ano != null) && (ngenero != null) && (line != null) && (imgfundo != null)) {
                nomeFilmeAssitido = nome;
                anoFilmeAssistido = ano;
                txtName2.setText(nome);
                //txtDuracao.setText(duracao + "min");
                txtAno2.setText(ano);
                txtTagline.setText(line);
                imgFundoAssistido = ("https://www.themoviedb.org/t/p/original" + imgfundo);
                if(usado ==  false) {
                    imgPosterAssistido = ("https://www.themoviedb.org/t/p/original" + imgposter);
                    Picasso
                            .get()
                            .load("https://www.themoviedb.org/t/p/original" + imgposter)
                            .into(imgPosterCritica);
                } else{
                    imgPosterAssistido = ("https://www.themoviedb.org/t/p/original" + PosterActivity.IDPosition);
                    Picasso
                            .get()
                            .load("https://www.themoviedb.org/t/p/original" + PosterActivity.IDPosition)
                            .into(imgPosterCritica);
                }



            } else {
                Toast.makeText(LogActivity.this, "Sem retorno de dados", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            // Se não receber um JSOn valido, informa ao usuário
            Toast.makeText(LogActivity.this, "JSON inválido", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        // obrigatório implementar, nenhuma ação executada
    }

    public void buscarFilmeAPI(String filmeID){
        Call<ArrayList<ItemFilme>> result = ApiClient.getUserService().getAllDataFilmeAPI(filmeID);
        result.enqueue(new Callback<ArrayList<ItemFilme>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemFilme>> call, Response<ArrayList<ItemFilme>> response) {
                if(response.isSuccessful()){
                    itemsfilme = response.body();

                    int b=0;

                    for(b=0; b<itemsfilme.size(); b++){
                        nomeFilmeAssitido = itemsfilme.get(0).getTitle();
                        anoFilmeAssistido = itemsfilme.get(0).getRelease_date();
                        txtName2.setText(itemsfilme.get(0).getTitle());
                        txtAno2.setText(itemsfilme.get(0).getRelease_date());
                        txtTagline.setText(itemsfilme.get(0).getTagline());
                        imgFundoAssistido = ("https://www.themoviedb.org/t/p/original" + itemsfilme.get(0).getBackdrop_path());
                        if(usado ==  false) {
                            imgPosterAssistido = ("https://www.themoviedb.org/t/p/original" + itemsfilme.get(0).getPoster_path());
                            Picasso
                                    .get()
                                    .load("https://www.themoviedb.org/t/p/original" + itemsfilme.get(0).getPoster_path())
                                    .into(imgPosterCritica);
                        } else{
                            imgPosterAssistido = ("https://www.themoviedb.org/t/p/original" + PosterActivity.IDPosition);
                            Picasso
                                    .get()
                                    .load("https://www.themoviedb.org/t/p/original" + PosterActivity.IDPosition)
                                    .into(imgPosterCritica);
                        }

                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemFilme>> call, Throwable t) {
                StyleableToast.makeText(LogActivity.this, "Ops! Parece que estamos tendo dificuldades com o nosso servidor", Toast.LENGTH_LONG, R.style.erroToast).show();
            }
        });
    }

    public FeedbackRequest feedbackRequest(){
        FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setIdMovie(idfilmeassistido);
        feedbackRequest.setFeedbackText(textInputEditText.getText().toString());
        feedbackRequest.setFeedbackDate(dataparasalvar);
        feedbackRequest.setFeedbackRate(ratingBar.getRating());
        return feedbackRequest;
    }

    public void salvarCritica(FeedbackRequest feedbackRequest){
        Call<ResponseBody> result = ApiClient.getUserService().saveFeedback(HomeActivity.IDUser, feedbackRequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    StyleableToast.makeText(LogActivity.this, "Sua crítica de " + nomeFilmeAssitido + " foi salva", Toast.LENGTH_LONG, R.style.exampleToast).show();

                }
                else{
                    StyleableToast.makeText(LogActivity.this, "Não foi possível salvar sua crítica", Toast.LENGTH_LONG, R.style.erroToast).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LogActivity.this, "Salvamento falhou" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public FeedbackERequest editedRequest(){
        FeedbackERequest feedbackERequest = new FeedbackERequest();
        feedbackERequest.setIdMovie(idfilmeassistido);
        feedbackERequest.setFeedbackText(textInputEditText.getText().toString());
        feedbackERequest.setFeedbackDate(dataparasalvar);
        feedbackERequest.setFeedbackRate(ratingBar.getRating());
        return feedbackERequest;
    }

    public void salvarCriticaEditada(FeedbackERequest feedbackERequest){
        Call<ResponseBody> result = ApiClient.getUserService().saveFeedbackEdit(HomeActivity.IDUser, DiaryActivity.idCriticaD, feedbackERequest);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    StyleableToast.makeText(LogActivity.this, "Sua crítica de " + nomeFilmeAssitido + " foi editada", Toast.LENGTH_LONG, R.style.exampleToast).show();

                }
                else{
                    StyleableToast.makeText(LogActivity.this, "Não foi possível salvar sua crítica", Toast.LENGTH_LONG, R.style.erroToast).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LogActivity.this, "Salvamento falhou" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}