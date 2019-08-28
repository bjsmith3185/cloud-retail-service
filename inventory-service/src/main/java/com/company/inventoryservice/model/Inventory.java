package com.company.inventoryservice.model;

import javax.validation.constraints.Min;
import java.util.Objects;

public class Inventory {
    private int id;
    @Min(1)
    private int productId;
    @Min(0)
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return id == inventory.id &&
                productId == inventory.productId &&
                quantity == inventory.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, quantity);
    }
}
