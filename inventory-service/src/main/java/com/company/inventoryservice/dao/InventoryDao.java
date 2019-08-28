package com.company.inventoryservice.dao;

import com.company.inventoryservice.model.Inventory;

import java.util.List;

public interface InventoryDao {
    Inventory addInventory(Inventory inventory);

    Inventory getInventory(int id);

    List<Inventory> getAllInventorys();

    Inventory getInventoryByProductId(int productId);

    void updateInventory(Inventory inventory);

    void deleteInventory(int id);

    int getQuantityByProduct(int productId);
}
