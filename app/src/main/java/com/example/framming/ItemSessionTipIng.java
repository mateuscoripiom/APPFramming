package com.example.framming;

import java.util.ArrayList;

public class ItemSessionTipIng {

    private String tokenCinema;
    private String idFilme;
    ArrayList<TipoIng> ingressos;
    private String dataSessao;
    private String horarioSessao;
    private String salaSessao;

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

    public ArrayList<TipoIng> getIngressos() {
        return ingressos;
    }

    public void setIngressos(ArrayList<TipoIng> ingressos) {
        this.ingressos = ingressos;
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

    public String getSalaSessao() {
        return salaSessao;
    }

    public void setSalaSessao(String salaSessao) {
        this.salaSessao = salaSessao;
    }
}
