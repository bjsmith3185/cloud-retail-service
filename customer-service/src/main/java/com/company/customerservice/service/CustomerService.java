package com.company.customerservice.service;

import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CustomerService {
    CustomerDao dao;

    @Autowired
    public CustomerService(CustomerDao dao) {
        this.dao = dao;
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        return dao.addCustomer(customer);
    }

    public void deleteCustomer(int id) {
        dao.deleteCustomer(id);
    }

    public void updateCustomer(Customer customer) {
        dao.updateCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return dao.getAllCustomers();
    }

    public Customer getCustomer(int id) {
        return dao.getCustomer(id);
    }
}
