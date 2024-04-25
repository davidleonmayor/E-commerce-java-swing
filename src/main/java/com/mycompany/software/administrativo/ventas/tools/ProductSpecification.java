package com.mycompany.software.administrativo.ventas.tools;

public class ProductSpecification {
    private String name;
    private float unitValue;
    private int quantity;

    public ProductSpecification(String name, float unitValue, int quantity) {
        this.name = name;
        this.unitValue = unitValue;
        this.quantity = quantity;
        
    }

    public String getName() {
        return name;
    }

    public float getUnitValue() {
        return unitValue;
    }

    public int getQuantity() {
        return quantity;
    }
}
