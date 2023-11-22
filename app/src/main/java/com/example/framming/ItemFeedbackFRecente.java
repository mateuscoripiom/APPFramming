package com.example.framming;

public class ItemFeedbackFRecente {
    String idFilme;
    String idCritica;
    Float notaCritica;
    String posterFilme;

    public ItemFeedbackFRecente(String idFilme, String idCritica, Float notaCritica, String posterFilme) {
        this.idFilme = idFilme;
        this.idCritica = idCritica;
        this.notaCritica = notaCritica;
        this.posterFilme = posterFilme;
    }

    public String getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(String idFilme) {
        this.idFilme = idFilme;
    }

    public String getIdCritica() {
        return idCritica;
    }

    public void setIdCritica(String idCritica) {
        this.idCritica = idCritica;
    }

    public Float getNotaCritica() {
        return notaCritica;
    }

    public void setNotaCritica(Float notaCritica) {
        this.notaCritica = notaCritica;
    }

    public String getPosterFilme() {
        return posterFilme;
    }

    public void setPosterFilme(String posterFilme) {
        this.posterFilme = posterFilme;
    }
}
