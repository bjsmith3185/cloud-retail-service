package com.company.inventoryservice.controller;

import com.company.inventoryservice.exception.NotFoundException;
import com.company.inventoryservice.model.Inventory;
import com.company.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryService service;

    @GetMapping("/quantity/{productid}")
    @ResponseStatus(HttpStatus.OK)
    public int getQuantity(@PathVariable("productid") int productId) {
        return service.getQuantityByProduct(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return service.saveInventory(inventory);
    }

    @GetMapping("/productid/{productid}")
    @ResponseStatus(HttpStatus.OK)
    public Inventory getInventoryByProductId(@PathVariable("productid") int productId) {
        return service.getInventoryByProduct(productId);
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Inventory getInventory(@PathVariable("id") int id) {
        Inventory inventory = service.getInventory(id);
        if (inventory == null)
            throw new NotFoundException("Naw");
        return inventory;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getAllInventory() {
        return service.getAllInventorys();
    }

    @PutMapping(value = "/id/{id}")
    public void updateInventory(@RequestBody Inventory inventory, @PathVariable int id) {
        if (id != inventory.getId()) {
            throw new IllegalArgumentException("Inventory ID on path must match the ID in the product object");
        }

        service.updateInventory(inventory);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteInventory(@PathVariable("id") int id) {
        service.deleteInventory(id);
    }
}
