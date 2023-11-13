package com.example.framming;

public class ItemNac {
    String idFilme;
    String posterFilme;

    public ItemNac(String idFilme, String posterFilme) {
        this.idFilme = idFilme;
        this.posterFilme = posterFilme;
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
}
