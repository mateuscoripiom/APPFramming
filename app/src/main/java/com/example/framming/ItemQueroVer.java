package com.example.framming;

public class ItemQueroVer {
    String id;
    String poster_path;
    String backdrop_path;

    public ItemQueroVer(String id, String poster_path, String backdrop_path) {
        this.id = id;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
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
}
