package com.example.framming;

public class FeedbackResponse {
    private String idFilme;
    private String textoCritica;
    private float notaCritica;
    private String dataCritica;
    private int qtdCurtidaCritica;

    public String getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(String idFilme) {
        this.idFilme = idFilme;
    }

    public String getTextoCritica() {
        return textoCritica;
    }

    public void setTextoCritica(String textoCritica) {
        this.textoCritica = textoCritica;
    }

    public float getNotaCritica() {
        return notaCritica;
    }

    public void setNotaCritica(float notaCritica) {
        this.notaCritica = notaCritica;
    }

    public String getDataCritica() {
        return dataCritica;
    }

    public void setDataCritica(String dataCritica) {
        this.dataCritica = dataCritica;
    }

    public int getQtdCurtidaCritica() {
        return qtdCurtidaCritica;
    }

    public void setQtdCurtidaCritica(int qtdCurtidaCritica) {
        this.qtdCurtidaCritica = qtdCurtidaCritica;
    }
}
