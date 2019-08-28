package com.company.customerservice.dao;

import com.company.customerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoJdbcTemplateImplTest {

    @Autowired
    CustomerDao dao;

    @Before
    public void setUp() throws Exception {
        List<Customer> customers = dao.getAllCustomers();
        for (Customer each : customers) {
            dao.deleteCustomer(each.getId());
        }
    }

    @Test
    public void addGetGetAllDeleteCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("J");
        customer.setLastName("G");
        customer.setStreet("John St");
        customer.setCity("Char");
        customer.setZip("28272");
        customer.setEmail("mentz@me.com");
        customer.setPhone("555-555-5555");

        customer = dao.addCustomer(customer);

        Customer customer1 = dao.getCustomer(customer.getId());
        assertEquals(customer, customer1);

        List<Customer> customers = dao.getAllCustomers();
        assertEquals(customers.size(), 1);


        dao.deleteCustomer(customer.getId());
        customer1 = dao.getCustomer(customer.getId());
        assertNull(customer1);
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("J");
        customer.setLastName("G");
        customer.setStreet("John St");
        customer.setCity("Char");
        customer.setZip("28272");
        customer.setEmail("mentz@me.com");
        customer.setPhone("555-555-5555");

        customer = dao.addCustomer(customer);

        customer.setCity("Chicago");
        dao.updateCustomer(customer);

        Customer customer1 = dao.getCustomer(customer.getId());
        assertEquals(customer, customer1);
    }

}