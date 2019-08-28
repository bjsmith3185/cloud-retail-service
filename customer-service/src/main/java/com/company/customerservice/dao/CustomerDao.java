package com.company.customerservice.dao;

import com.company.customerservice.model.Customer;

import java.util.List;

public interface CustomerDao {
    Customer addCustomer(Customer customer);

    Customer getCustomer(int id);

    List<Customer> getAllCustomers();

    void updateCustomer(Customer customer);

    void deleteCustomer(int id);
}
