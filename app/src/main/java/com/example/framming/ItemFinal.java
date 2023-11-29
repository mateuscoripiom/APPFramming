package com.example.framming;

public class ItemFinal {
    String idpop;
    String image;
    Integer ordemArray;

    public ItemFinal(String idpop, String image, Integer ordemArray) {
        this.idpop = idpop;
        this.image = image;
        this.ordemArray = ordemArray;
    }

    public String getIdpop() {
        return idpop;
    }

    public void setIdpop(String idpop) {
        this.idpop = idpop;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getOrdemArray() {
        return ordemArray;
    }

    public void setOrdemArray(Integer ordemArray) {
        this.ordemArray = ordemArray;
    }
}
