package com.example.framming;

import androidx.loader.content.AsyncTaskLoader;
import android.content.Context;

import androidx.annotation.Nullable;

public class CarregaPoster extends AsyncTaskLoader<String> {
    private String mMovieString;
    CarregaPoster(Context context, String movieString) {
        super(context);
        mMovieString = movieString;
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.buscaFilmePoster(mMovieString, "images");
    }
}