package com.example.framming;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

public class CarregaFilme extends AsyncTaskLoader<String> {
private String mQueryString;
    CarregaFilme(Context context, String queryString) {
        super(context);
        mQueryString = queryString;
    }
@Override
protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
        }
@Nullable
@Override
public String loadInBackground() {
        return NetworkUtils.buscaFilmeString(mQueryString);
        }
}
