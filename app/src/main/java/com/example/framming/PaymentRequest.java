package com.example.framming;

public class PaymentRequest {
    private String numCard;
    private String nameCard;
    private String cpfUser;
    private String valCard;
    private String cvvCard;

    public String getNumCard() {
        return numCard;
    }

    public void setNumCard(String numCard) {
        this.numCard = numCard;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public String getCpfUser() {
        return cpfUser;
    }

    public void setCpfUser(String cpfUser) {
        this.cpfUser = cpfUser;
    }

    public String getValCard() {
        return valCard;
    }

    public void setValCard(String valCard) {
        this.valCard = valCard;
    }

    public String getCvvCard() {
        return cvvCard;
    }

    public void setCvvCard(String cvvCard) {
        this.cvvCard = cvvCard;
    }
}
