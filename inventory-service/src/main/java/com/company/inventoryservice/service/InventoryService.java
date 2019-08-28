package com.company.inventoryservice.service;

import com.company.inventoryservice.dao.InventoryDao;
import com.company.inventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class InventoryService {

    InventoryDao dao;

    @Autowired
    public InventoryService(InventoryDao dao) {
        this.dao = dao;
    }

    @Transactional
    public Inventory saveInventory(Inventory inventory) {
        inventory = dao.addInventory(inventory);
        return inventory;
    }

    public int getQuantityByProduct(int productId) {
        return dao.getQuantityByProduct(productId);
    }

    public List<Inventory> getAllInventorys() {
        return dao.getAllInventorys();
    }

    public Inventory getInventory(int id) {
        return dao.getInventory(id);
    }

    public void updateInventory(Inventory inventory) {
        dao.updateInventory(inventory);
    }

    public void deleteInventory(int id) {
        dao.deleteInventory(id);
    }

    public Inventory getInventoryByProduct(int productId) {
        return dao.getInventoryByProductId(productId);
    }
}
