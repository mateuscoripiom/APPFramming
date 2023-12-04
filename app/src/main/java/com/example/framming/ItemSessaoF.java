package com.example.framming;

import java.util.ArrayList;
import java.util.List;

public class ItemSessaoF {
    String idSessao;
    String tokenCinema;
    String dataSessao;
    String horarioSessao;
    String idFilme;
    String salaSessao;

    public ItemSessaoF(String idSessao, String tokenCinema, String dataSessao, String horarioSessao, String idFilme, String salaSessao) {
        this.idSessao = idSessao;
        this.tokenCinema = tokenCinema;
        this.dataSessao = dataSessao;
        this.horarioSessao = horarioSessao;
        this.idFilme = idFilme;
        this.salaSessao = salaSessao;
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

    public String getSalaSessao() {
        return salaSessao;
    }

    public void setSalaSessao(String salaSessao) {
        this.salaSessao = salaSessao;
    }
}
