package com.example.framming;

public class TicketsFinaisC {
    private String posterFilme;
    private String salaSessao;
    private String idFilme;
    private String idSessao;
    private int qtdIngressos;
    private String tokenCinema;
    private String dataSessao;
    private String horarioSessao;
    private String nomeFilme;
    private String backdropFilme;
    private String nomeCinema;

    public TicketsFinaisC(String posterFilme, String salaSessao, String idFilme, String idSessao, int qtdIngressos, String tokenCinema, String dataSessao, String horarioSessao, String nomeFilme, String backdropFilme, String nomeCinema) {
        this.posterFilme = posterFilme;
        this.salaSessao = salaSessao;
        this.idFilme = idFilme;
        this.idSessao = idSessao;
        this.qtdIngressos = qtdIngressos;
        this.tokenCinema = tokenCinema;
        this.dataSessao = dataSessao;
        this.horarioSessao = horarioSessao;
        this.nomeFilme = nomeFilme;
        this.backdropFilme = backdropFilme;
        this.nomeCinema = nomeCinema;
    }

    public String getPosterFilme() {
        return posterFilme;
    }

    public void setPosterFilme(String posterFilme) {
        this.posterFilme = posterFilme;
    }

    public String getSalaSessao() {
        return salaSessao;
    }

    public void setSalaSessao(String salaSessao) {
        this.salaSessao = salaSessao;
    }

    public String getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(String idFilme) {
        this.idFilme = idFilme;
    }

    public String getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(String idSessao) {
        this.idSessao = idSessao;
    }

    public int getQtdIngressos() {
        return qtdIngressos;
    }

    public void setQtdIngressos(int qtdIngressos) {
        this.qtdIngressos = qtdIngressos;
    }

    public String getTokenCinema() {
        return tokenCinema;
    }

    public void setTokenCinema(String tokenCinema) {
        this.tokenCinema = tokenCinema;
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

    public String getNomeFilme() {
        return nomeFilme;
    }

    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    public String getBackdropFilme() {
        return backdropFilme;
    }

    public void setBackdropFilme(String backdropFilme) {
        this.backdropFilme = backdropFilme;
    }

    public String getNomeCinema() {
        return nomeCinema;
    }

    public void setNomeCinema(String nomeCinema) {
        this.nomeCinema = nomeCinema;
    }
}
