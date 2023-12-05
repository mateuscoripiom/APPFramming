package com.example.framming;

public class TicketRequest {
    private String idMovie;
    private String idTicket;
    private String numCardPayment;
    private String numTickets;

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(String idTicket) {
        this.idTicket = idTicket;
    }

    public String getNumCardPayment() {
        return numCardPayment;
    }

    public void setNumCardPayment(String numCardPayment) {
        this.numCardPayment = numCardPayment;
    }

    public String getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(String numTickets) {
        this.numTickets = numTickets;
    }
}
