package com.mycompany.software.administrativo.ventas.tools;

public class Product {
    private String name;
    private float unitValue;
    private int quantity;
//    private int idBillDetailsProduct ;

    public Product(String name, float unitValue, int quantity /* , int idBillDetailsProduct */) {
        this.name = name;
        this.unitValue = unitValue;
        this.quantity = quantity;
//        this.idBillDetailsProduct = idBillDetailsProduct;
    }
    
    public String getProductName() {
        return name;
    }

    public float getUnitValue() {
        return unitValue;
    }

    public int getQuantity() {
        return quantity;
    }

//    public int getIdBillDetailsProduct() {
//        return idBillDetailsProduct;
//    }
}
