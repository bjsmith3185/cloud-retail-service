package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoJdbcTemplateImplTest {

    @Autowired
    InvoiceItemDao dao;

    @Autowired
    InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {
        List<InvoiceItem> invoiceItems = dao.getAllInvoiceItems();
        for (InvoiceItem each : invoiceItems) {
            dao.deleteInvoiceItem(each.getId());
        }
    }

    @Test
    public void addGetDeleteGetAllInvoice() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2012, 12, 22));

        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(5);
        invoiceItem.setUnitPrice(new BigDecimal(23.00).setScale(2));


        invoiceItem = dao.addInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = dao.getInvoiceItemById(invoiceItem.getId());
        assertEquals(invoiceItem1, invoiceItem);

        List<InvoiceItem> invoiceItems = dao.getAllInvoiceItems();
        assertEquals(invoiceItems.size(), 1);


        dao.deleteInvoiceItem(invoiceItem.getId());
        invoiceItem1 = dao.getInvoiceItemById(invoiceItem.getId());
        assertNull(invoiceItem1);
    }

    @Test
    public void updateInvoiceItem() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(5);
        invoiceItem.setUnitPrice(new BigDecimal(23.00).setScale(2));


        invoiceItem = dao.addInvoiceItem(invoiceItem);
        invoiceItem.setQuantity(10);
        dao.updateInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = dao.getInvoiceItemById(invoiceItem.getId());
        assertEquals(invoiceItem1, invoiceItem);
    }
}