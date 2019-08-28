package com.company.retailservice.dto;

import java.util.Objects;

public class InStockProducts {


    private int productId;
    private int quantity;
    private Inventory inventory;


    // getters / setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }


    // equals/ hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InStockProducts that = (InStockProducts) o;
        return productId == that.productId &&
                quantity == that.quantity &&
                inventory.equals(that.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity, inventory);
    }


    // to string


    @Override
    public String toString() {
        return "InStockProducts{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                ", inventory=" + inventory +
                '}';
    }
}
