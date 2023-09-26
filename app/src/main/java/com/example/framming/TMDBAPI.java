package com.example.framming;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class TMDBAPI extends AsyncTaskLoader<String> {
    private String mMovieString;
    TMDBAPI(Context context, String movieString) {
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
        return NetworkUtils.buscaFilme(mMovieString);
    }
}