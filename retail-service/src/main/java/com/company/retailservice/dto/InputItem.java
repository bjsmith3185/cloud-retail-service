package com.company.retailservice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class InputItem {

    @NotEmpty(message = "must contain a valid product ID")
    private int productId;
    @NotEmpty(message = "must contain a valid quantity")
    private int quantity;


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



    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputItem inputItem = (InputItem) o;
        return productId == inputItem.productId &&
                quantity == inputItem.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }


    // to string


    @Override
    public String toString() {
        return "InputItem{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
