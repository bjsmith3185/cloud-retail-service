package com.company.adminservice.feign;

import com.company.adminservice.dto.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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



    @RequestMapping(value = "/inventory/product/{productid}", method = RequestMethod.GET)
    public Inventory getInventoryByProductId(@PathVariable int productid);



}
