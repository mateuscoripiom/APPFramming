package com.example.framming;

public class ItemFavFinal {
    String idFilme;
    String posterFilme;

    String backdrop;

    public ItemFavFinal(String idFilme, String posterFilme, String backdrop) {
        this.idFilme = idFilme;
        this.posterFilme = posterFilme;
        this.backdrop = backdrop;
    }

    public String getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(String idFilme) {
        this.idFilme = idFilme;
    }

    public String getPosterFilme() {
        return posterFilme;
    }

    public void setPosterFilme(String posterFilme) {
        this.posterFilme = posterFilme;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }
}
