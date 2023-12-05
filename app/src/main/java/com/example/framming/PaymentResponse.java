package com.example.framming;

public class PaymentResponse {
    String idPagamento;
    String idUsuario;
    String numeroCartao;
    String nomeCartao;
    String cpfTitular;
    String validadeCartao;
    String cvvCartao;

    public PaymentResponse(String idPagamento, String idUsuario, String numeroCartao, String nomeCartao, String cpfTitular, String validadeCartao, String cvvCartao) {
        this.idPagamento = idPagamento;
        this.idUsuario = idUsuario;
        this.numeroCartao = numeroCartao;
        this.nomeCartao = nomeCartao;
        this.cpfTitular = cpfTitular;
        this.validadeCartao = validadeCartao;
        this.cvvCartao = cvvCartao;
    }

    public String getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(String idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeCartao() {
        return nomeCartao;
    }

    public void setNomeCartao(String nomeCartao) {
        this.nomeCartao = nomeCartao;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }

    public String getValidadeCartao() {
        return validadeCartao;
    }

    public void setValidadeCartao(String validadeCartao) {
        this.validadeCartao = validadeCartao;
    }

    public String getCvvCartao() {
        return cvvCartao;
    }

    public void setCvvCartao(String cvvCartao) {
        this.cvvCartao = cvvCartao;
    }
}
