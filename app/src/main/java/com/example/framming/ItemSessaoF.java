package com.example.framming;

import java.util.List;

public class ItemSessaoF {
    String idSessao;
    String tokenCinema;
    String dataSessao;
    String horarioSessao;
    String idFilme;
    List<TipoIng> ingressos;

    public ItemSessaoF(String idSessao, String tokenCinema, String dataSessao, String horarioSessao, String idFilme, List<TipoIng> ingressos) {
        this.idSessao = idSessao;
        this.tokenCinema = tokenCinema;
        this.dataSessao = dataSessao;
        this.horarioSessao = horarioSessao;
        this.idFilme = idFilme;
        this.ingressos = ingressos;
    }

    public String getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(String idSessao) {
        this.idSessao = idSessao;
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

    public String getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(String idFilme) {
        this.idFilme = idFilme;
    }

    public List<TipoIng> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<TipoIng> ingressos) {
        this.ingressos = ingressos;
    }
}
