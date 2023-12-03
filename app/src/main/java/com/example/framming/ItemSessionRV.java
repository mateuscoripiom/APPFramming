package com.example.framming;

public class ItemSessionRV {
    String idSessao;
    String idFilme;
    String tokenCinema;
    String qtdIngressosSessao;
    String salaSessao;
    String dataSessao;
    String horarioSessao;

    public ItemSessionRV(String idSessao, String idFilme, String tokenCinema, String qtdIngressosSessao, String salaSessao, String dataSessao, String horarioSessao) {
        this.idSessao = idSessao;
        this.idFilme = idFilme;
        this.tokenCinema = tokenCinema;
        this.qtdIngressosSessao = qtdIngressosSessao;
        this.salaSessao = salaSessao;
        this.dataSessao = dataSessao;
        this.horarioSessao = horarioSessao;
    }

    public String getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(String idSessao) {
        this.idSessao = idSessao;
    }

    public String getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(String idFilme) {
        this.idFilme = idFilme;
    }

    public String getTokenCinema() {
        return tokenCinema;
    }

    public void setTokenCinema(String tokenCinema) {
        this.tokenCinema = tokenCinema;
    }

    public String getQtdIngressosSessao() {
        return qtdIngressosSessao;
    }

    public void setQtdIngressosSessao(String qtdIngressosSessao) {
        this.qtdIngressosSessao = qtdIngressosSessao;
    }

    public String getSalaSessao() {
        return salaSessao;
    }

    public void setSalaSessao(String salaSessao) {
        this.salaSessao = salaSessao;
    }

    public String getDataSessao() {
        return dataSessao;
    }

    public void setDataSessao(String dataSessao) {
        this.dataSessao = dataSessao;
    }

    public String getHorarioSessao() {
        return horarioSessao;
    }

    public void setHorarioSessao(String horarioSessao) {
        this.horarioSessao = horarioSessao;
    }
}
