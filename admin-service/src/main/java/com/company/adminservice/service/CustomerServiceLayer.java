package com.company.adminservice.service;

import com.company.adminservice.dto.Customer;
import com.company.adminservice.feign.CustomerServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerServiceLayer {

    @Autowired
    private CustomerServiceClient customerServiceClient;


//    public CustomerServiceLayer(CustomerServiceClient customerServiceClient) {
//        this.customerServiceClient = customerServiceClient;
//    }



    // CREATE CUSTOMER
    public Customer createCustomer(Customer customer) {
        return customerServiceClient.createCustomer(customer);
    }

    // FIND CUSTOMER BY ID
    public Customer findCustomer(int id) {
        return customerServiceClient.getCustomerById(id);
      }

    // FINDALL CUSTOMER
    public List<Customer> findAllCustomers() {
        System.out.println("in the customer service layer");
        return customerServiceClient.getAllCustomers();
    }

    // UPDATE CUSTOMER
    public void updateCustomer(int id, Customer customer) {
        customerServiceClient.updateCustomer(id, customer);
    }

    // DELETE CUSTOMER BY ID
    public void deleteCustomer(int id) {
         customerServiceClient.deleteCustomer(id);
    }




















}
