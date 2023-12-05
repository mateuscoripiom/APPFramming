package com.example.framming;

public class TicketResponse {
    private String idSessao;
    private int qtdTickets;

    public String getIdSessao() {
        return idSessao;
    }

    public void setIdSessao(String idSessao) {
        this.idSessao = idSessao;
    }

    public int getQtdTickets() {
        return qtdTickets;
    }

    public void setQtdTickets(int qtdTickets) {
        this.qtdTickets = qtdTickets;
    }
}
