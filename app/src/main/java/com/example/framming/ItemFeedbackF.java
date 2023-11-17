package com.example.framming;

public class ItemFeedbackF {
    String idFilme;
    String idCritica;
    Float notaCritica;
    String dataCritica;
    String posterFilme;
    String nomeFilme;
    String anoFilme;
    String originalFilme;
    String fundoFilme;

    public ItemFeedbackF(String idFilme, String idCritica, Float notaCritica, String dataCritica, String posterFilme, String fundoFilme, String nomeFilme, String anoFilme, String originalFilme) {
        this.idFilme = idFilme;
        this.idCritica = idCritica;
        this.notaCritica = notaCritica;
        this.dataCritica = dataCritica;
        this.posterFilme = posterFilme;
        this.fundoFilme = fundoFilme;
        this.nomeFilme = nomeFilme;
        this.anoFilme = anoFilme;
        this.originalFilme = originalFilme;
    }

    public String getFundoFilme() {
        return fundoFilme;
    }

    public void setFundoFilme(String fundoFilme) {
        this.fundoFilme = fundoFilme;
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

    public String getPosterFilme() {
        return posterFilme;
    }

    public void setPosterFilme(String posterFilme) {
        this.posterFilme = posterFilme;
    }

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getAnoFilme() {
        return anoFilme;
    }

    public void setAnoFilme(String anoFilme) {
        this.anoFilme = anoFilme;
    }

    public String getOriginalFilme() {
        return originalFilme;
    }

    public void setOriginalFilme(String originalFilme) {
        this.originalFilme = originalFilme;
    }
}
