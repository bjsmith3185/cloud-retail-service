package com.company.retailservice.feign;

import com.company.retailservice.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "customer-service")
public interface CustomerServiceClient {

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public Customer createCustomer(@RequestBody @Valid Customer customer);

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers();

    @RequestMapping(value = "/customers/id/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable int id);

    @RequestMapping(value = "/customers/id/{id}", method = RequestMethod.PUT)
    public void updateCustomer(@PathVariable int id, @RequestBody Customer customer);

    @RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable int id);


}
