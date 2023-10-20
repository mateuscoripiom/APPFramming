package com.example.framming;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CadastroReponse {
    @SerializedName("idUsuario")
    @Expose
    private String idUsuario;
    @SerializedName("iconUsuario")
    @Expose
    private String iconUsuario;
    @SerializedName("nomeUsuario")
    @Expose
    private String nomeUsuario;
    @SerializedName("nickUsuario")
    @Expose
    private String nickUsuario;
    @SerializedName("emailUsuario")
    @Expose
    private String emailUsuario;
    @SerializedName("senhaUsuario")
    @Expose
    private String senhaUsuario;
    @SerializedName("qtdPontos")
    @Expose
    private Integer qtdPontos;
    @SerializedName("tipoUsuario")
    @Expose
    private String tipoUsuario;
    @SerializedName("token")
    @Expose
    private String token;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIconUsuario() {
        return iconUsuario;
    }

    public void setIconUsuario(String iconUsuario) {
        this.iconUsuario = iconUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNickUsuario() {
        return nickUsuario;
    }

    public void setNickUsuario(String nickUsuario) {
        this.nickUsuario = nickUsuario;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public Integer getQtdPontos() {
        return qtdPontos;
    }

    public void setQtdPontos(Integer qtdPontos) {
        this.qtdPontos = qtdPontos;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
