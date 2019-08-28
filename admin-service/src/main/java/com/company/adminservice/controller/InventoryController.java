package com.company.adminservice.controller;

import com.company.adminservice.dto.Inventory;
import com.company.adminservice.service.InventoryServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class InventoryController {


    @Autowired
    private InventoryServiceLayer service;


    @RequestMapping(value = "/administration/inventory", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory createInventory(@RequestBody Inventory inventory, Principal principal) {
        return service.createInventory(inventory);
     }

    @RequestMapping(value = "/administration/inventory", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getAllInventorys(Principal principal) {
        List<Inventory> inventory = service.findAllInventorys();
        return inventory;
    }

    @RequestMapping(value = "/administration/inventory/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Inventory getInventoryById(@PathVariable("id") int id, Principal principal) {
        return service.findInventory(id);
    }

    @RequestMapping(value = "/administration/inventory/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInventory(@PathVariable("id") int id, @RequestBody Inventory inventory, Principal principal) {
        service.updateInventory(id, inventory);
    }

    @RequestMapping(value = "/administration/inventory/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventory(@PathVariable(name = "id") int id, Principal principal) {
        service.deleteInventory(id);
    }





}
