package com.company.adminservice.service;

import com.company.adminservice.dto.Inventory;
import com.company.adminservice.feign.InventoryServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryServiceLayer {

    @Autowired
    private InventoryServiceClient inventoryServiceClient;




    // CREATE INVENTORY
    public Inventory createInventory(Inventory inventory) {
        return inventoryServiceClient.createInventory(inventory);
    }


    // FIND INVENTORY BY ID
    public Inventory findInventory(int id) {
        return inventoryServiceClient.getInventoryById(id);
    }

    // FINDALL INVENTORY
    public List<Inventory> findAllInventorys() {
        return inventoryServiceClient.getAllInventorys();
     }

    // UPDATE INVENTORY
    public void updateInventory(int id, Inventory inventory) {
        inventoryServiceClient.updateInventory(id, inventory);
    }

    // DELETE INVENTORY BY ID
    public void deleteInventory(int id) {
        inventoryServiceClient.deleteInventory(id);
    }



    // FIND INVENTORY BY PRODUCT ID
    public Inventory findInventoryByProductId(int id) {
        return inventoryServiceClient.getInventoryByProductId(id);
     }






}
