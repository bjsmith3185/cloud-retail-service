package com.company.inventoryservice.dao;

import com.company.inventoryservice.model.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InventoryDaoJdbcTemplateImplTest {

    @Autowired
    InventoryDao dao;


    @Before
    public void setUp() throws Exception {
        List<Inventory> inventories = dao.getAllInventorys();
        for (Inventory inventory: inventories) {
            dao.deleteInventory(inventory.getId());
        }
    }

    @Test
    public void addGetGetAllDeleteInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(1);

        inventory = dao.addInventory(inventory);

        Inventory inventory1 = dao.getInventory(inventory.getId());
        assertEquals(inventory, inventory1);

        List<Inventory> inventories = dao.getAllInventorys();
        assertEquals(inventories.size(), 1);

        dao.deleteInventory(inventory.getId());
        inventory1 = dao.getInventory(inventory.getId());
        assertNull(inventory1);
    }


    @Test
    public void updateInventory() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(1);

        inventory = dao.addInventory(inventory);

        inventory.setQuantity(3);
        dao.updateInventory(inventory);

        Inventory inventory1 = dao.getInventory(inventory.getId());
        assertEquals(inventory, inventory1);
    }

    @Test
    public void getInventoryByProduct() {
        Inventory inventory = new Inventory();
        inventory.setProductId(1);
        inventory.setQuantity(1);

        inventory = dao.addInventory(inventory);

        Inventory inventory1 = dao.getInventoryByProductId(1);
        assertEquals(inventory, inventory1);
    }

}