package com.company.adminservice.controller;

import com.company.adminservice.dto.Customer;
import com.company.adminservice.dto.Product;
import com.company.adminservice.service.CustomerServiceLayer;
import com.company.adminservice.service.ProductServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductServiceLayer service;


    @RequestMapping(value = "/administration/products", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        product = service.createProduct(product);
        return product;
    }

    @RequestMapping(value = "/administration/products", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        List<Product> product = service.findAllProducts();
        return product;
    }

    @RequestMapping(value = "/administration/products/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable("id") int id) {
        Product product = service.findProduct(id);
        return product;
    }

    @RequestMapping(value = "/administration/products/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        service.updateProduct(id, product);
    }

    @RequestMapping(value = "/administration/products/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(name = "id") int id) {
        service.deleteProduct(id);
    }


}
