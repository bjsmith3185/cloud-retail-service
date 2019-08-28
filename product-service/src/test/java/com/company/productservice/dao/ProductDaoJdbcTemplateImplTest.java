package com.company.productservice.dao;

import com.company.productservice.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProductDaoJdbcTemplateImplTest {

    @Autowired
    ProductDao productDao;

    @Before
    public void setUp() throws Exception {
        List<Product> products = productDao.getAllProducts();
        for (Product each : products) {
            productDao.deleteProduct(each.getId());
        }
    }

    @Test
    public void addGetDeleteGetAllProduct() {

        Product product = new Product();
        product.setProductName("Xbox");
        product.setProductDescription("Game console");
        product.setListPrice(new BigDecimal(200.00).setScale(2));
        product.setUnitCost(new BigDecimal(225.00).setScale(2));

        product = productDao.addProduct(product);

        Product productFromDb = productDao.getProduct(product.getId());
        assertEquals(product, productFromDb);

        List<Product> products = productDao.getAllProducts();
        assertEquals(products.size(), 1);


        productDao.deleteProduct(product.getId());
        productFromDb = productDao.getProduct(product.getId());
        assertNull(productFromDb);
    }

    @Test
    public void updateProduct() {
        Product product = new Product();
        product.setProductName("Xbox");
        product.setProductDescription("Game console");
        product.setListPrice(new BigDecimal(200.00).setScale(2));
        product.setUnitCost(new BigDecimal(225.00).setScale(2));

        product = productDao.addProduct(product);

        product.setProductName("PS4");
        productDao.updateProduct(product);

        Product product1 = productDao.getProduct(product.getId());
        assertEquals(product, product1);

    }

}