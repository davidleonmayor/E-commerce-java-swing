package com.mycompany.software.administrativo.ventas.tools;

public class Bill {
    private int clientId;
    private int sallerId;
    private int boxId;
    
    public Bill (int clientId, int sallerId, int boxId) {
    this.clientId = clientId;
    this.sallerId = sallerId;
    this.boxId  = boxId;
    }
    
    public int clientId () {
        return this.clientId;
    }
    
    public int sallerId () {
        return this.sallerId;
    }
    
    public int boxId () {
        return this.boxId;
    }
}
