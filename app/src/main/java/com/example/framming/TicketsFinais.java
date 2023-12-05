package com.example.framming;

public class TicketsFinais {
    private String salaSessao;
    private String sessaoId;
    private int qtdIngressos;
    private String tokenCinema;
    private String idFilme;
    private String dataSessao;
    private String horarioSessao;

    public TicketsFinais(String salaSessao, String sessaoId, int qtdIngressos, String tokenCinema, String idFilme, String dataSessao, String horarioSessao) {
        this.salaSessao = salaSessao;
        this.sessaoId = sessaoId;
        this.qtdIngressos = qtdIngressos;
        this.tokenCinema = tokenCinema;
        this.idFilme = idFilme;
        this.dataSessao = dataSessao;
        this.horarioSessao = horarioSessao;
    }

    public String getSalaSessao() {
        return salaSessao;
    }

    public void setSalaSessao(String salaSessao) {
        this.salaSessao = salaSessao;
    }

    public String getSessaoId() {
        return sessaoId;
    }

    public void setSessaoId(String sessaoId) {
        this.sessaoId = sessaoId;
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

    public String getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(String idFilme) {
        this.idFilme = idFilme;
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
