package com.company.invoiceservice.controller;

import com.company.invoiceservice.dao.InvoiceDao;
import com.company.invoiceservice.dao.InvoiceItemDao;
import com.company.invoiceservice.exception.NotFoundException;
import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import com.company.invoiceservice.service.InvoiceService;
import com.company.invoiceservice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    @Autowired
    InvoiceService service;

    @PostMapping
    public InvoiceViewModel createInvoice(@RequestBody InvoiceViewModel invoiceViewModel) {

        System.out.println("!!!!!!!");
        System.out.println(invoiceViewModel.getInvoice().toString());
        System.out.println(invoiceViewModel.getInvoiceItems().toString());



        return service.saveInvoice(invoiceViewModel);
    }

    @GetMapping(value = "/id/{id}")
    public InvoiceViewModel getInvoice(@PathVariable int id) {
        InvoiceViewModel invoiceViewModel = service.findInvoice(id);
        if (invoiceViewModel == null)
            throw new NotFoundException("Invoice could not be retrieved");
        return invoiceViewModel;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {
        return service.getAllInvoices();
    }

    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable int id) {
        service.removeInvoice(id);
    }

    @PutMapping(value = "/id/{id}")
    public void updateInvoice(@RequestBody InvoiceViewModel invoice, @PathVariable int id) {
//        if (id != invoice.getInvoiceId()) {
        if (id != invoice.getInvoice().getInvoiceId()) {
            throw new IllegalArgumentException("Invoice ID on path must match the ID in the invoice object");
        }

        service.updateInvoice(invoice);
    }

    @PostMapping("/invoiceitems")
    public InvoiceItem createInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        InvoiceItem invoiceItem1 = service.saveInvoiceItem(invoiceItem);
        return invoiceItem1;
    }

    @GetMapping(value = "/invoiceitems/id/{id}")
    public InvoiceItem getInvoiceItem(@PathVariable int id) {
        InvoiceItem invoiceItem = service.findInvoiceItem(id);

        if (invoiceItem == null) {
            throw new NotFoundException("Invoice Item could not be retrieved for id " + id);
        }

        return invoiceItem;
    }

    @GetMapping("/invoiceitems")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItem> getAllInvoiceItems() {
        return service.getAllInvoiceItems();
    }

    @PutMapping(value = "/invoiceitems/id/{id}")
    public void updateInvoiceItem(@RequestBody InvoiceItem invoiceItem, @PathVariable int id) {
        if (id != invoiceItem.getId()) {
            throw new IllegalArgumentException("Invoice Item ID on path must match the ID in the invoice item object");
        }
        service.updateInvoiceItem(invoiceItem);
    }

    @GetMapping("/invoiceitems/{invoiceid}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceItem> getItemByInvoice(@PathVariable("invoiceid") int invoiceId) {
        return service.getItemByInvoice(invoiceId);
    }

    @GetMapping("/invoiceitems/total/{invoiceid}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTotal(@PathVariable("{invoiceid}") int invoiceId) {
        return service.getTotalByInvoice(invoiceId);
    }

    @GetMapping("/invoiceitems/quantity/{invoiceid}")
    @ResponseStatus(HttpStatus.OK)
    public int getQuantity(@PathVariable("{invoiceid}") int invoiceId) {
        return service.getTotalQuantity(invoiceId);
    }

    @DeleteMapping(value = "/invoiceitems/{id}")
    public void deleteInvoiceItem(@PathVariable(name = "id") int id) {
        service.removeInvoiceItem(id);
    }
}
