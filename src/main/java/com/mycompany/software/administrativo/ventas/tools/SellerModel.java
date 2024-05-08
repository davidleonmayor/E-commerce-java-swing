package com.mycompany.software.administrativo.ventas.tools;

public class SellerModel {

    private int id;
    private int document;
    private String names;
    private String lastNames;

    public SellerModel(int id, int document, String names, String lastNames) {
        this.id = id;
        this.document = document;
        this.names = names;
        this.lastNames = lastNames;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    @Override
    public String toString() {
        return "Client{"
                + "id=" + id
                + ", document=" + document
                + ", names='" + names + '\''
                + ", lastNames='" + lastNames + '\''
                + '}';
    }

}
