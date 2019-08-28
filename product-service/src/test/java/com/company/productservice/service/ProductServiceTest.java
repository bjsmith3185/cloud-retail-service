package com.company.productservice.service;

import com.company.productservice.dao.ProductDao;
import com.company.productservice.dao.ProductDaoJdbcTemplateImpl;
import com.company.productservice.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ProductServiceTest {

    ProductDao dao;
    ProductService service;

    @Before
    public void setUp() throws Exception {
        setUpProductDaoMock();

        service = new ProductService(dao);

    }

    private void setUpProductDaoMock() {
        dao = mock(ProductDaoJdbcTemplateImpl.class);

        Product product = new Product();
        product.setId(1);
        product.setProductName("J");
        product.setProductDescription("M");
        product.setListPrice(new BigDecimal(200).setScale(2));
        product.setUnitCost(new BigDecimal(100).setScale(2));

        Product product1 = new Product();

        product1.setProductName("J");
        product1.setProductDescription("M");
        product1.setListPrice(new BigDecimal(200).setScale(2));
        product1.setUnitCost(new BigDecimal(100).setScale(2));

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        doReturn(product).when(dao).addProduct(product1);
        doReturn(product).when(dao).getProduct(1);
        doReturn(productList).when(dao).getAllProducts();
    }

    @Test
    public void saveFindFindAllProduct() {
        Product product = new Product();
        product.setProductName("J");
        product.setProductDescription("M");
        product.setListPrice(new BigDecimal(200).setScale(2));
        product.setUnitCost(new BigDecimal(100).setScale(2));

        product = service.saveProduct(product);
        Product fromService = service.getProduct(product.getId());
        List<Product> productList = service.getAllProducts();
        assertEquals(productList.size(), 1);
        assertEquals(product, fromService);
    }
}