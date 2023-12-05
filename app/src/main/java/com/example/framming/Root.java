package com.example.framming;


import java.util.ArrayList;

public class Root{
    public String idUsuario;
    public int idLista;
    public int idFilme;
    public String descricaoLista;
    public int qtdCurtidaLista;
    public ArrayList<Movie> movies;

    public Root(String idUsuario, int idLista, int idFilme, String descricaoLista, int qtdCurtidaLista, ArrayList<Movie> movies) {
        this.idUsuario = idUsuario;
        this.idLista = idLista;
        this.idFilme = idFilme;
        this.descricaoLista = descricaoLista;
        this.qtdCurtidaLista = qtdCurtidaLista;
        this.movies = movies;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLista() {
        return idLista;
    }

    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public String getDescricaoLista() {
        return descricaoLista;
    }

    public void setDescricaoLista(String descricaoLista) {
        this.descricaoLista = descricaoLista;
    }

    public int getQtdCurtidaLista() {
        return qtdCurtidaLista;
    }

    public void setQtdCurtidaLista(int qtdCurtidaLista) {
        this.qtdCurtidaLista = qtdCurtidaLista;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}

