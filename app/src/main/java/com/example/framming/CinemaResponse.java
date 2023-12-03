package com.example.framming;

public class CinemaResponse {
    private String idCinema;
    private String nomeCinema;
    private String enderecoCinema;
    private String tokenCinema;
    private String qtdSala;

    public CinemaResponse(String idCinema, String nomeCinema, String enderecoCinema, String tokenCinema, String qtdSala) {
        this.idCinema = idCinema;
        this.nomeCinema = nomeCinema;
        this.enderecoCinema = enderecoCinema;
        this.tokenCinema = tokenCinema;
        this.qtdSala = qtdSala;
    }

    public String getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(String idCinema) {
        this.idCinema = idCinema;
    }

    public String getNomeCinema() {
        return nomeCinema;
    }

    public void setNomeCinema(String nomeCinema) {
        this.nomeCinema = nomeCinema;
    }

    public String getEnderecoCinema() {
        return enderecoCinema;
    }

    public void setEnderecoCinema(String enderecoCinema) {
        this.enderecoCinema = enderecoCinema;
    }

    public String getTokenCinema() {
        return tokenCinema;
    }

    public void setTokenCinema(String tokenCinema) {
        this.tokenCinema = tokenCinema;
    }

    public String getQtdSala() {
        return qtdSala;
    }

    public void setQtdSala(String qtdSala) {
        this.qtdSala = qtdSala;
    }
}
