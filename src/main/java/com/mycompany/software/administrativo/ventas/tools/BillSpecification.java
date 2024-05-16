package com.mycompany.software.administrativo.ventas.tools;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class BillSpecification {
    private List<Product> products;
    
    public BillSpecification(int id, Date fecha, Time hora, String clientName, String clientLastName, String sellerName, String sellerLastName, int boxNumber, String paymentMethod) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.clientName = clientName;
        this.clientLastName = clientLastName;
        this.sellerName = sellerName;
        this.sellerLastName = sellerLastName;
        this.boxNumber = boxNumber;
        this.paymentMethod = paymentMethod;
    }

    public BillSpecification() {}
    
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
    
    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public String getSellerLastName() {
        return sellerLastName;
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public void setSellerLastName(String sellerLastName) {
        this.sellerLastName = sellerLastName;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    private int id;
    private Date fecha;
    private Time hora;
    private String clientName;
    private String clientLastName;
    private String sellerName;
    private String sellerLastName;
    private int boxNumber;
    private String paymentMethod;
}
