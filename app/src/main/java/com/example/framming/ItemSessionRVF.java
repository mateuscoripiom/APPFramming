package com.example.framming;

import java.util.ArrayList;
import java.util.List;

public class ItemSessionRVF {
    String idSessao;
    String idFilme;
    String tokenCinema;
    String nomeCinema;
    String qtdIngressosSessao;
    String salaSessao;
    String dataSessao;
    String horarioSessao;

    public ItemSessionRVF(String idSessao, String idFilme, String tokenCinema, String nomeCinema, String qtdIngressosSessao, String salaSessao, String dataSessao, String horarioSessao) {
        this.idSessao = idSessao;
        this.idFilme = idFilme;
        this.tokenCinema = tokenCinema;
        this.nomeCinema = nomeCinema;
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

    public String getNomeCinema() {
        return nomeCinema;
    }

    public void setNomeCinema(String nomeCinema) {
        this.nomeCinema = nomeCinema;
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
