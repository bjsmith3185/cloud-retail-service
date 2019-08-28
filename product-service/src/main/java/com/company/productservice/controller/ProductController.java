package com.company.productservice.controller;

import com.company.productservice.dao.ProductDao;
import com.company.productservice.exception.NotFoundException;
import com.company.productservice.model.Product;
import com.company.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
@RefreshScope
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        Product product1 = service.saveProduct(product);
        return product1;
    }

    @GetMapping(value = "/id/{id}")
    public Product getProduct(@PathVariable int id) {
        Product product = service.getProduct(id);

        if (product == null) {
            throw new NotFoundException("Product could not be retrieved for id " + id);
        }

        return product;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @PutMapping(value = "/id/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable int id) {
        if (id != product.getId()) {
            throw new IllegalArgumentException("Product ID on path must match the ID in the product object");
        }

        service.updateProduct(product);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable(name = "id") int id) {
        service.removeProduct(id);
    }

}
