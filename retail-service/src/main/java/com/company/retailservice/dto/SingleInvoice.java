package com.company.retailservice.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class SingleInvoice {

    private int invoiceId;
    private BigDecimal orderTotalPrice;

    private List<OrderProducts> orderItems;




    // getters / setters

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public List<OrderProducts> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderProducts> orderItems) {
        this.orderItems = orderItems;
    }


    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleInvoice that = (SingleInvoice) o;
        return invoiceId == that.invoiceId &&
                orderTotalPrice.equals(that.orderTotalPrice) &&
                orderItems.equals(that.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, orderTotalPrice, orderItems);
    }


    // to string


    @Override
    public String toString() {
        return "SingleInvoice{" +
                "invoiceId=" + invoiceId +
                ", orderTotalPrice=" + orderTotalPrice +
                ", orderItems=" + orderItems +
                '}';
    }
}
