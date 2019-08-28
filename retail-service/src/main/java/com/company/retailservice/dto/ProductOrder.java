package com.company.retailservice.dto;

import java.util.Objects;

public class ProductOrder {
    private int productId;
    private int quantity;

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
        ProductOrder that = (ProductOrder) o;
        return productId == that.productId &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }

}
