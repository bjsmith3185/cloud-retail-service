package com.company.retailservice.feign;

import com.company.retailservice.dto.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "inventory-service")
public interface InventoryServiceClient {

    @RequestMapping(value = "/inventory", method = RequestMethod.POST)
    public Inventory createInventory(@RequestBody Inventory inventory);

    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public List<Inventory> getAllInventorys();

    @RequestMapping(value = "/inventory/id/{id}", method = RequestMethod.GET)
    public Inventory getInventoryById(@PathVariable int id);

    @RequestMapping(value = "/inventory/id/{id}", method = RequestMethod.PUT)
    public void updateInventory(@PathVariable int id, @RequestBody Inventory inventory);

    @RequestMapping(value = "/inventory/{id}", method = RequestMethod.DELETE)
    public void deleteInventory(@PathVariable int id);

    @GetMapping("/inventory/quantity/{productid}")
    @ResponseStatus(HttpStatus.OK)
    public int getQuantity(@PathVariable("productid") int productId);

    @RequestMapping(value = "/inventory/productid/{productid}", method = RequestMethod.GET)
    public Inventory getInventoryByProductId(@PathVariable("productid") int productId );



}
