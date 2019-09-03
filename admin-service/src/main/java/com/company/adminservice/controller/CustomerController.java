package com.company.adminservice.controller;

import com.company.adminservice.dto.Customer;
import com.company.adminservice.service.CustomerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerServiceLayer service;


    @RequestMapping(value = "/administration/customers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer) {
        customer = service.createCustomer(customer);
        return customer;
    }

    @RequestMapping(value = "/administration/customers", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        System.out.println("getting all customers");
        List<Customer> customer = service.findAllCustomers();
        return customer;
    }

    @RequestMapping(value = "/administration/customers/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable("id") int id) {
        Customer customer = service.findCustomer(id);
        return customer;
    }

    @RequestMapping(value = "/administration/customers/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer) {
        service.updateCustomer(id, customer);
    }

    @RequestMapping(value = "/administration/customers/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable(name = "id") int id) {
        service.deleteCustomer(id);
    }



}
