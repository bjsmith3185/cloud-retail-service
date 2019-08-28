package com.company.productservice.service;

import com.company.productservice.dao.ProductDao;
import com.company.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProductService {
    ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional
    public Product saveProduct(Product product) {
        return productDao.addProduct(product);
    }

    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    public void removeProduct(int id) {
        productDao.deleteProduct(id);
    }

    public List<Product> getAllProducts () {
        return productDao.getAllProducts();
    }

    public Product getProduct(int id) {
        return productDao.getProduct(id);
    }
}
