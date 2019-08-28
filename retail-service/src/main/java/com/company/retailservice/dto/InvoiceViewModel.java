package com.company.retailservice.dto;

import java.util.List;
import java.util.Objects;

public class InvoiceViewModel {


    private Invoice invoice;
    private List<InvoiceItem> invoiceItems;

    // getters / setters

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }


    // equals / hash

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return invoice.equals(that.invoice) &&
                invoiceItems.equals(that.invoiceItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoice, invoiceItems);
    }


    // to string


    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "invoice=" + invoice +
                ", invoiceItems=" + invoiceItems +
                '}';
    }
}
