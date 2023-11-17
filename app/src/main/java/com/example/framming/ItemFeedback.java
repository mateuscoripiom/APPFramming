package com.example.framming;

public class ItemFeedback {
    String idFilme;
    String idCritica;
    Float notaCritica;
    String dataCritica;
    String feedbackCritica;

    public ItemFeedback(String idFilme, String idCritica, Float notaCritica, String dataCritica, String feedbackCritica) {
        this.idFilme = idFilme;
        this.idCritica = idCritica;
        this.notaCritica = notaCritica;
        this.dataCritica = dataCritica;
        this.feedbackCritica = feedbackCritica;
    }

    public String getFeedbackCritica() {
        return feedbackCritica;
    }

    public void setFeedbackCritica(String feedbackCritica) {
        this.feedbackCritica = feedbackCritica;
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

    public float getNotaCritica() {
        return notaCritica;
    }

    public void setNotaCritica(Float notaCritica) {
        this.notaCritica = notaCritica;
    }

    public String getDataCritica() {
        return dataCritica;
    }

    public void setDataCritica(String dataCritica) {
        this.dataCritica = dataCritica;
    }
}
