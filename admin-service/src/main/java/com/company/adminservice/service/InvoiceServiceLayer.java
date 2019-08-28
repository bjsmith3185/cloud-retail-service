package com.company.adminservice.service;

import com.company.adminservice.dto.InvoiceViewModel;
import com.company.adminservice.feign.InvoiceServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceServiceLayer {



    @Autowired
    private InvoiceServiceClient invoiceServiceClient;



    // CREATE INVOICE
    public InvoiceViewModel createInvoice(InvoiceViewModel invoiceViewModel) {
        return invoiceServiceClient.createInvoice(invoiceViewModel);
    }

    // FIND INVOICE BY ID
    public InvoiceViewModel findInvoice(int id) {
        return invoiceServiceClient.getInvoiceById(id);
    }

    // FINDALL INVOICE
    public List<InvoiceViewModel> findAllInvoices() {
         return invoiceServiceClient.getAllInvoices();
    }

    // UPDATE INVOICE
    public void updateInvoice(int id, InvoiceViewModel invoiceViewModel) {
        invoiceServiceClient.updateInvoice(id, invoiceViewModel);
    }

    // DELETE INVOICE BY ID
    public void deleteInvoice(int id) {
         invoiceServiceClient.deleteInvoice(id);
    }



//
//    // CREATE INVOICEITEM
//    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
//        return invoiceServiceClient.createInvoiceItem(invoiceItem);
//    }
//
//    // FIND INVOICEITEM BY ID
//    public InvoiceItem findInvoiceItem(int id) {
//        return invoiceServiceClient.getInvoiceItemById(id);
//    }
//
//    // FINDALL INVOICEITEM
//    public List<InvoiceItem> findAllInvoiceItems() {
//        return invoiceServiceClient.getAllInvoiceItems();
//    }
//
//    // UPDATE INVOICEITEM
//    public void updateInvoiceItem(int id, InvoiceItem invoiceItem) {
//        invoiceServiceClient.updateInvoiceItem(id, invoiceItem);
//    }
//
//    // DELETE INVOICEITEM BY ID
//    public void deleteInvoiceItem(int id) {
//        invoiceServiceClient.deleteInvoiceItem(id);
//    }






}
