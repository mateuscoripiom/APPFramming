package com.example.framming;

public class TipoIng {
    String idIngresso;
    String valorIngresso;
    String tipoIngresso;

    public TipoIng(String idIngresso, String valorIngresso, String tipoIngresso) {
        this.idIngresso = idIngresso;
        this.valorIngresso = valorIngresso;
        this.tipoIngresso = tipoIngresso;
    }

    public String getIdIngresso() {
        return idIngresso;
    }

    public void setIdIngresso(String idIngresso) {
        this.idIngresso = idIngresso;
    }

    public String getValorIngresso() {
        return valorIngresso;
    }

    public void setValorIngresso(String valorIngresso) {
        this.valorIngresso = valorIngresso;
    }

    public String getTipoIngresso() {
        return tipoIngresso;
    }

    public void setTipoIngresso(String tipoIngresso) {
        this.tipoIngresso = tipoIngresso;
    }
}
