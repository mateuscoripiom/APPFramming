package com.example.framming;

public class MovieFinal {
    public String id;
    public String poster_path;
    public String backdrop_path;
    public Integer ordemArray;

    public MovieFinal(String id, String poster_path, String backdrop_path, Integer ordemArray) {
        this.id = id;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.ordemArray = ordemArray;
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

    public Integer getOrdemArray() {
        return ordemArray;
    }

    public void setOrdemArray(Integer ordemArray) {
        this.ordemArray = ordemArray;
    }
}
