package com.company.retailservice.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderProducts {

    private int invoiceItemId;
    private int quantity;
    private BigDecimal listPrice;
    private BigDecimal totalPrice;
    private String productName;
    private String productDescription;



    // getters / setters

    public int getInvoiceItemId() {
        return invoiceItemId;
    }

    public void setInvoiceItemId(int invoiceItemId) {
        this.invoiceItemId = invoiceItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProducts that = (OrderProducts) o;
        return invoiceItemId == that.invoiceItemId &&
                quantity == that.quantity &&
                listPrice.equals(that.listPrice) &&
                totalPrice.equals(that.totalPrice) &&
                productName.equals(that.productName) &&
                productDescription.equals(that.productDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceItemId, quantity, listPrice, totalPrice, productName, productDescription);
    }


    // to string


    @Override
    public String toString() {
        return "OrderProducts{" +
                "invoiceItemId=" + invoiceItemId +
                ", quantity=" + quantity +
                ", listPrice=" + listPrice +
                ", totalPrice=" + totalPrice +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                '}';
    }
}
