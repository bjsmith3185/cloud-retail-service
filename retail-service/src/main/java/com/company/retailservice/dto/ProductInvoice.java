package com.company.retailservice.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductInvoice {


    private int customerId;
    private int invoiceId;
    private int quantity;
    private BigDecimal totalPrice;
    private Product product;
    private InvoiceItem invoiceItem;
    private Inventory inventory;

    // gettters / setters

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public InvoiceItem getInvoiceItem() {
        return invoiceItem;
    }

    public void setInvoiceItem(InvoiceItem invoiceItem) {
        this.invoiceItem = invoiceItem;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInvoice that = (ProductInvoice) o;
        return customerId == that.customerId &&
                invoiceId == that.invoiceId &&
                quantity == that.quantity &&
                totalPrice.equals(that.totalPrice) &&
                product.equals(that.product) &&
                invoiceItem.equals(that.invoiceItem) &&
                inventory.equals(that.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, invoiceId, quantity, totalPrice, product, invoiceItem, inventory);
    }


    // to string


    @Override
    public String toString() {
        return "ProductInvoice{" +
                "customerId=" + customerId +
                ", invoiceId=" + invoiceId +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", product=" + product +
                ", invoiceItem=" + invoiceItem +
                ", inventory=" + inventory +
                '}';
    }
}
