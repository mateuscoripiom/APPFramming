package com.example.framming;

public class ItemCriticaFinal {
    String idCritica;
    String idFilme;
    String idUsuario;
    String textoCritica;
    float notaCritica;
    String dataCritica;
    String qtdCurtidaCritica;
    String nickUsuario;
    String iconUsuario;

    public ItemCriticaFinal(String idCritica, String idFilme, String idUsuario, String textoCritica, float notaCritica, String dataCritica, String qtdCurtidaCritica, String nickUsuario, String iconUsuario) {
        this.idCritica = idCritica;
        this.idFilme = idFilme;
        this.idUsuario = idUsuario;
        this.textoCritica = textoCritica;
        this.notaCritica = notaCritica;
        this.dataCritica = dataCritica;
        this.qtdCurtidaCritica = qtdCurtidaCritica;
        this.nickUsuario = nickUsuario;
        this.iconUsuario = iconUsuario;
    }

    public String getIdCritica() {
        return idCritica;
    }

    public void setIdCritica(String idCritica) {
        this.idCritica = idCritica;
    }

    public String getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(String idFilme) {
        this.idFilme = idFilme;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getQtdCurtidaCritica() {
        return qtdCurtidaCritica;
    }

    public void setQtdCurtidaCritica(String qtdCurtidaCritica) {
        this.qtdCurtidaCritica = qtdCurtidaCritica;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    public String getIconUsuario() {
        return iconUsuario;
    }

    public void setIconUsuario(String iconUsuario) {
        this.iconUsuario = iconUsuario;
    }
}
