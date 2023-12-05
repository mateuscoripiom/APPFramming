package com.example.framming;

public class ListaResponse {
    String idLista;
    String idUsuario;
    String descricaoLista;
    String qtdCurtidaLista;

    public ListaResponse(String idLista, String idUsuario, String descricaoLista, String qtdCurtidaLista) {
        this.idLista = idLista;
        this.idUsuario = idUsuario;
        this.descricaoLista = descricaoLista;
        this.qtdCurtidaLista = qtdCurtidaLista;
    }

    public String getIdLista() {
        return idLista;
    }

    public void setIdLista(String idLista) {
        this.idLista = idLista;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDescricaoLista() {
        return descricaoLista;
    }

    public void setDescricaoLista(String descricaoLista) {
        this.descricaoLista = descricaoLista;
    }

    public String getQtdCurtidaLista() {
        return qtdCurtidaLista;
    }

    public void setQtdCurtidaLista(String qtdCurtidaLista) {
        this.qtdCurtidaLista = qtdCurtidaLista;
    }
}
