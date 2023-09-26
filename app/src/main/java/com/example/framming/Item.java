package com.example.framming;

public class Item {
    String idpop;
    String image;

    public Item(String idpop, String image) {
        this.idpop = idpop;
        this.image = image;
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
}
