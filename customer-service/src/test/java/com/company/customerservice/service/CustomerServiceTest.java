package com.company.customerservice.service;

import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.dao.CustomerDaoJdbcTemplateImpl;
import com.company.customerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class CustomerServiceTest {

    CustomerDao dao;
    CustomerService service;

    @Before
    public void setUp() throws Exception {
        setUpCustomerMock();

        service = new CustomerService(dao);
    }

    private void setUpCustomerMock() {
        dao = mock(CustomerDaoJdbcTemplateImpl.class);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("J");
        customer.setLastName("M");
        customer.setStreet("Mam");
        customer.setCity("Char");
        customer.setZip("28262");
        customer.setEmail("me@me.com");
        customer.setPhone("336-755-5555");

        Customer customer1 = new Customer();
        customer1.setFirstName("J");
        customer1.setLastName("M");
        customer1.setStreet("Mam");
        customer1.setCity("Char");
        customer1.setZip("28262");
        customer1.setEmail("me@me.com");
        customer1.setPhone("336-755-5555");

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        doReturn(customer).when(dao).addCustomer(customer1);
        doReturn(customer).when(dao).getCustomer(1);
        doReturn(customers).when(dao).getAllCustomers();
    }

    @Test
    public void saveFindFindAllCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("J");
        customer.setLastName("M");
        customer.setStreet("Mam");
        customer.setCity("Char");
        customer.setZip("28262");
        customer.setEmail("me@me.com");
        customer.setPhone("336-755-5555");

        customer = service.saveCustomer(customer);
        Customer fromService = service.getCustomer(customer.getId());
        List<Customer> customers = service.getAllCustomers();

        assertEquals(customer, fromService);
        assertEquals(customers.size(), 1);
    }


}