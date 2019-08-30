package com.company.retailservice.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

public class OrderRequestView {

    @NotNull(message = "must contain a customer object")
    private Customer customer;
    @NotNull( message = "must contain a list of InputItem objects")
    private List<InputItem> products;


    // getters / setters

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<InputItem> getProducts() {
        return products;
    }

    public void setProducts(List<InputItem> products) {
        this.products = products;
    }


    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRequestView that = (OrderRequestView) o;
        return customer.equals(that.customer) &&
                products.equals(that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, products);
    }


    // to string

    @Override
    public String toString() {
        return "OrderRequestView{" +
                "customer=" + customer +
                ", products=" + products +
                '}';
    }
}
