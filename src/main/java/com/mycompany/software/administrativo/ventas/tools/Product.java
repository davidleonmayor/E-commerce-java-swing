package com.mycompany.software.administrativo.ventas.tools;

public class Product {
    private String name;
    private float unitValue;
    private int quantity;

    public Product(String name, float unitValue, int quantity) {
        this.name = name;
        this.unitValue = unitValue;
        this.quantity = quantity;
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
}
