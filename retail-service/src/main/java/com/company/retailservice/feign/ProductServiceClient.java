package com.company.retailservice.feign;

import com.company.retailservice.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductServiceClient {

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product);

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts();

    @RequestMapping(value = "/products/id/{id}", method = RequestMethod.GET)
    public Product getProductById(@PathVariable int id);

    @RequestMapping(value = "/products/id/{id}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable int id, @RequestBody Product product);

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int id);





//    @RequestMapping(value = "/products", method = RequestMethod.GET)
//    public List<Product> getAllProducts();
//
//    @RequestMapping(value = "/products/id/{id}", method = RequestMethod.GET)
//    public Product getProductById(@PathVariable int id);


}
