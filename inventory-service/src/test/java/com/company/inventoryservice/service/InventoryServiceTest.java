package com.company.inventoryservice.service;

import com.company.inventoryservice.dao.InventoryDao;
import com.company.inventoryservice.dao.InventoryDaoJdbcTemplateImpl;
import com.company.inventoryservice.model.Inventory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class InventoryServiceTest {

    InventoryDao dao;
    InventoryService service;

    @Before
    public void setUp() throws Exception {
        setUpInventoryDaoMock();

        service = new InventoryService(dao);
    }

    private void setUpInventoryDaoMock() {
        dao = mock(InventoryDaoJdbcTemplateImpl.class);
        Inventory inventory = new Inventory();
        inventory.setId(1);
        inventory.setProductId(1);
        inventory.setQuantity(1);

        Inventory inventory1 = new Inventory();
        inventory1.setProductId(1);
        inventory1.setQuantity(1);

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory);

        doReturn(inventory).when(dao).addInventory(inventory1);
        doReturn(inventoryList).when(dao).getAllInventorys();
        doReturn(inventory).when(dao).getInventory(1);
        doReturn(inventory).when(dao).getInventoryByProductId(1);

    }

    @Test
    public void saveFindFindAllInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(1);

        inventory = service.saveInventory(inventory);

        Inventory inventory1 = service.getInventory(inventory.getId());
        List<Inventory> inventoryList = service.getAllInventorys();
        assertEquals(inventoryList.size(), 1);
        assertEquals(inventory, inventory1);
    }

    @Test
    public void getInventoryByProduct() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(1);

        inventory = service.saveInventory(inventory);

        Inventory inventory2 = service.getInventoryByProduct(inventory.getProductId());

        assertEquals(inventory2, inventory);
    }
}