package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoJdbcTemplateImplTest {

    @Autowired
    InvoiceDao dao;

    @Before
    public void setUp() throws Exception {
        List<Invoice> invoices = dao.getAllInvoices();
        for (Invoice each : invoices) {
            dao.deleteInvoice(each.getInvoiceId());
        }
    }

    @Test
    public void addGetDeleteGetAllInvoice() {

        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2012, 12, 22));

        invoice = dao.addInvoice(invoice);

        Invoice invoice1 = dao.getInvoiceById(invoice.getInvoiceId());
        assertEquals(invoice, invoice);

        List<Invoice> invoices = dao.getAllInvoices();
        assertEquals(invoices.size(), 1);


        dao.deleteInvoice(invoice.getInvoiceId());
        invoice1 = dao.getInvoiceById(invoice.getInvoiceId());
        assertNull(invoice1);
    }


    @Test
    public void updateInvoiceItem() {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2012, 12, 22));

        invoice = dao.addInvoice(invoice);

        invoice.setCustomerId(2);
        dao.updateInvoice(invoice);

        Invoice invoice1 = dao.getInvoiceById(invoice.getInvoiceId());
        assertEquals(invoice, invoice);
    }

}