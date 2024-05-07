package com.mycompany.software.administrativo.ventas.tools;

public class Bill {
    private int clientId;
    private int sallerId;
    private Integer boxId;
    private String date;
    private String time;

    public Bill(int clientId, int sallerId, String date, String time) {
        this.clientId = clientId;
        this.sallerId = sallerId;
        this.boxId = 1;
        this.date = date;
        this.time = time;
    }

    public int getClientId() {
        return this.clientId;
    }

    public int getSellerId() {
        return this.sallerId;
    }

    public int getBoxId() {
        return this.boxId;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }
}
