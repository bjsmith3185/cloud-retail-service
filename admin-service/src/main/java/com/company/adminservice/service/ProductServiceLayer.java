package com.company.adminservice.service;

import com.company.adminservice.dto.Product;
import com.company.adminservice.feign.ProductServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceLayer {

    @Autowired
    private ProductServiceClient productServiceClient;


    // CREATE PRODUCT
    public Product createProduct(Product product) {
         return productServiceClient.createProduct(product);
     }


    // FIND PRODUCT BY ID
    public Product findProduct(int id) {
        return productServiceClient.getProductById(id);
    }

    // FINDALL PRODUCT
    public List<Product> findAllProducts() {
        return productServiceClient.getAllProducts();
    }

    // UPDATE PRODUCT
    public void updateProduct(int id, Product product) {
        productServiceClient.updateProduct(id, product);
    }


    // DELETE PRODUCT BY ID
    public void deleteProduct(int id) {
        productServiceClient.deleteProduct(id);
    }


}
