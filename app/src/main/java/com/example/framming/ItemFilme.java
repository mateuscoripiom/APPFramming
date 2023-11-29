package com.example.framming;

import java.util.ArrayList;
import java.util.List;

public class ItemFilme {
    String id;
    String poster_path;
    String backdrop_path;
    String original_title;
    String overview;
    String release_date;
    String runtime;
    String title;
    String notaFilme;
    String qtdVisualizacaoFilme;
    String situacaoFilme;
    String tagline;
    ArrayList<NameGenre> genres;


    public ArrayList<NameGenre> getGenre() {
        return genres;
    }

    public void setGenre(ArrayList<NameGenre> genres) {
        this.genres = genres;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotaFilme() {
        return notaFilme;
    }

    public void setNotaFilme(String notaFilme) {
        this.notaFilme = notaFilme;
    }

    public String getQtdVisualizacaoFilme() {
        return qtdVisualizacaoFilme;
    }

    public void setQtdVisualizacaoFilme(String qtdVisualizacaoFilme) {
        this.qtdVisualizacaoFilme = qtdVisualizacaoFilme;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getSituacaoFilme() {
        return situacaoFilme;
    }

    public void setSituacaoFilme(String situacaoFilme) {
        this.situacaoFilme = situacaoFilme;
    }
}
