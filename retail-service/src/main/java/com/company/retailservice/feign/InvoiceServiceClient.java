package com.company.retailservice.feign;

import com.company.retailservice.dto.Invoice;
import com.company.retailservice.dto.InvoiceItem;
import com.company.retailservice.dto.InvoiceViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "invoice-service")
public interface InvoiceServiceClient {

    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
    public InvoiceViewModel createInvoice(@RequestBody InvoiceViewModel invoiceViewModel);

    @RequestMapping(value = "/invoices/id/{id}", method = RequestMethod.GET)
    public InvoiceViewModel getInvoiceById(@PathVariable int id);

    @RequestMapping(value = "/invoices", method = RequestMethod.GET)
    public List<InvoiceViewModel> getAllInvoices();

    @RequestMapping(value = "/invoices/id/{id}", method = RequestMethod.PUT)
    public void updateInvoice(@PathVariable int id, @RequestBody InvoiceViewModel invoiceViewModel);

    @RequestMapping(value = "/invoices/id/{id}", method = RequestMethod.DELETE)
    public void deleteInvoice(@PathVariable int id);

    @GetMapping("/invoiceitems/{invoiceid}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getItemByInvoice(@PathVariable("invoiceid") int invoiceId);

    @GetMapping("/invoiceitems/total/{invoiceid}")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getTotal(@PathVariable("{invoiceid}") int invoiceId);


    @GetMapping("/invoiceitems/total/{invoiceid}")
    @ResponseStatus(HttpStatus.OK)
    public int getQuantity(@PathVariable("{invoiceid}") int invoiceId);


//    @RequestMapping(value = "/invoices", method = RequestMethod.POST)
//    public InvoiceViewModel createInvoice(@RequestBody InvoiceViewModel invoiceViewModel);
//
//
////    @RequestMapping(value = "/invoices/id/{id}", method = RequestMethod.GET)
////    public InvoiceViewModel getInvoiceById(@PathVariable int id);
//
////    @RequestMapping(value = "/InvoiceItems", method = RequestMethod.GET)
////    public List<InvoiceItem> getAllInvoiceItemss();


}
