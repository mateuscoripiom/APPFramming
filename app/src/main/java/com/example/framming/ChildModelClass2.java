package com.example.framming;

public class ChildModelClass2 {
    String tokenCinema;
    String horarioSessao;

    public ChildModelClass2(String tokenCinema, String horarioSessao) {
        this.tokenCinema = tokenCinema;
        this.horarioSessao = horarioSessao;
    }

    public String getTokenCinema() {
        return tokenCinema;
    }

    public void setTokenCinema(String tokenCinema) {
        this.tokenCinema = tokenCinema;
    }

    public String getHorarioSessao() {
        return horarioSessao;
    }

    public void setHorarioSessao(String horarioSessao) {
        this.horarioSessao = horarioSessao;
    }
}
