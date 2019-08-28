package com.company.invoiceservice.service;

import com.company.invoiceservice.dao.InvoiceDao;
import com.company.invoiceservice.dao.InvoiceDaoJdbcTemplateImpl;
import com.company.invoiceservice.dao.InvoiceItemDao;
import com.company.invoiceservice.dao.InvoiceItemDaoJdbcTemplateImpl;
import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import com.company.invoiceservice.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class InvoiceServiceTest {
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;

    InvoiceService service;

    @Before
    public void setUp() throws Exception {
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();

        service = new InvoiceService(invoiceDao, invoiceItemDao);

    }

    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(1);
        invoice.setPurchaseDate(LocalDate.of(2022, 12, 12));

        Invoice invoice1 = new Invoice();
//        invoice1.setInvoiceId(1);
        invoice1.setCustomerId(1);
        invoice1.setPurchaseDate(LocalDate.of(2022, 12, 12));

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);

        doReturn(invoice).when(invoiceDao).addInvoice(invoice1);
        doReturn(invoices).when(invoiceDao).getAllInvoices();
        doReturn(invoice).when(invoiceDao).getInvoiceById(1);
    }

    private void setUpInvoiceItemDaoMock() {
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImpl.class);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setInventoryId(1);
        invoiceItem.setQuantity(1);
        invoiceItem.setUnitPrice(new BigDecimal(200.00).setScale(2));

        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceId(1);
        invoiceItem1.setInventoryId(1);
        invoiceItem1.setQuantity(1);
        invoiceItem1.setUnitPrice(new BigDecimal(200.00).setScale(2));

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        doReturn(invoiceItem).when(invoiceItemDao).addInvoiceItem(invoiceItem1);
        doReturn(invoiceItem).when(invoiceItemDao).getInvoiceItemById(1);
        doReturn(invoiceItems).when(invoiceItemDao).getAllInvoiceItems();
    }

//    @Test
//    public void saveFindFindAllInvoice() {
//        InvoiceViewModel ivm = new InvoiceViewModel();
//
//        List<Invoice> invoices = new ArrayList<>();
//        ivm.setInvoiceId(1);
//        ivm.setCustomerId(1);
//        ivm.setPurchaseDate(LocalDate.of(2022, 12, 12));
//
//        InvoiceItem invoiceItem = new InvoiceItem();
//        invoiceItem.setInvoiceId(1);
//        invoiceItem.setInventoryId(1);
//        invoiceItem.setQuantity(1);
//        invoiceItem.setUnitPrice(new BigDecimal(200.00).setScale(2));
//        invoiceItem = service.saveInvoiceItem(invoiceItem);
//        List<InvoiceItem> invoiceItems = new ArrayList<>();
//        invoiceItems.add(invoiceItem);
//
//        ivm.setInvoiceItems(invoiceItems);
//        ivm = service.saveInvoice(ivm);
//
//        InvoiceViewModel fromService = service.findInvoice(ivm.getInvoiceId());
//        assertEquals(ivm, fromService);
//        List<InvoiceViewModel> fromList = service.getAllInvoices();
//        assertEquals(fromList.size(), 1);
//
//
//
//    }


}