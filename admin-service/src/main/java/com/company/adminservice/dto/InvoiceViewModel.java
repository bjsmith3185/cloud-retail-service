package com.company.adminservice.dto;

import java.util.List;
import java.util.Objects;

public class InvoiceViewModel extends Invoice {



    private List<InvoiceItem> invoiceItems;

    // getters / setters

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
        if (!super.equals(o)) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return invoiceItems.equals(that.invoiceItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), invoiceItems);
    }


    // to string


    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "invoiceItems=" + invoiceItems +
                '}';
    }
}
