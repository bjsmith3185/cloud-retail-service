package com.company.invoiceservice.service;

import com.company.invoiceservice.dao.InvoiceDao;
import com.company.invoiceservice.dao.InvoiceItemDao;
import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import com.company.invoiceservice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class InvoiceService {

    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;

    @Autowired
    public InvoiceService(InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao) {
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    public int getTotalQuantity(int invoiceId) {
        return invoiceItemDao.totalQuantityByInvoice(invoiceId);
    }

    public List<InvoiceItem> getItemByInvoice(int invoiceId) {
        return invoiceItemDao.getInvoiceItemsByInvoiceId(invoiceId);
    }

    public BigDecimal getTotalByInvoice(int invoiceId) {
        return invoiceItemDao.totalPriceItems(invoiceId);
    }

    @Transactional
    public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem) {
        return invoiceItemDao.addInvoiceItem(invoiceItem);
    }

    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemDao.getAllInvoiceItems();
    }

    public InvoiceItem findInvoiceItem(int id) {
        return invoiceItemDao.getInvoiceItemById(id);
    }

    public void removeInvoiceItem(int id)
    {
        invoiceItemDao.deleteInvoiceItem(id);
    }


    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) {

        Invoice invoice = new Invoice();
        invoice.setCustomerId(invoiceViewModel.getInvoice().getCustomerId());
        invoice.setPurchaseDate(invoiceViewModel.getInvoice().getPurchaseDate());

        System.out.println("!!!!!!!!!!!!!");
        System.out.println(invoice.toString());

        invoice = invoiceDao.addInvoice(invoice);
        invoiceViewModel.setInvoice(invoice);
        List<InvoiceItem> invoiceItems = invoiceViewModel.getInvoiceItems();
        if(invoiceViewModel.getInvoiceItems() != null) {
            invoiceItems.stream().forEach(invoiceItem -> {
                invoiceItem.setInvoiceId(invoiceViewModel.getInvoice().getInvoiceId());
                invoiceItemDao.addInvoiceItem(invoiceItem);
            });
        }


        invoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getInvoiceId());
        invoiceViewModel.setInvoiceItems(invoiceItems);

        return invoiceViewModel;
    }



    public InvoiceViewModel findInvoice(int id) {
        Invoice invoice = invoiceDao.getInvoiceById(id);
        if(invoice == null )
            return null;
        else
            return buildInvoiceViewModel(invoice);
    }

    public void removeInvoice(int id)
    {
        List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(id);
        for (InvoiceItem invoiceitem: invoiceItems) {
            invoiceItemDao.deleteInvoiceItem(invoiceitem.getId());
        }
        invoiceDao.deleteInvoice(id);
    }

    public void updateInvoice(InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceViewModel.getInvoice().getInvoiceId());
        invoice.setCustomerId(invoiceViewModel.getInvoice().getCustomerId());
        invoice.setPurchaseDate(invoiceViewModel.getInvoice().getPurchaseDate());
        invoiceDao.updateInvoice(invoice);

        List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getInvoiceId());
        invoiceItems.stream().forEach(invoiceItem -> {
            invoiceItemDao.deleteInvoiceItem(invoiceItem.getId());
        });

        invoiceItems = invoiceViewModel.getInvoiceItems();
        invoiceItems.stream().forEach(invoiceItem -> {
            invoiceItem.setInvoiceId(invoiceViewModel.getInvoice().getInvoiceId());
            invoiceItemDao.addInvoiceItem(invoiceItem);
        });
    }


    public List<InvoiceViewModel> getAllInvoices() {
        List<Invoice> invoices = invoiceDao.getAllInvoices();
        List<InvoiceViewModel> invoiceViewModels = new ArrayList<>();
        for (Invoice invoice: invoices) {
            invoiceViewModels.add(buildInvoiceViewModel(invoice));
        }
        return invoiceViewModels;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();

        invoiceViewModel.setInvoice(invoice);

        List<InvoiceItem> invoiceItems = invoiceItemDao.getInvoiceItemsByInvoiceId(invoice.getInvoiceId());
        invoiceViewModel.setInvoiceItems(invoiceItems);
        return invoiceViewModel;
    }


}
