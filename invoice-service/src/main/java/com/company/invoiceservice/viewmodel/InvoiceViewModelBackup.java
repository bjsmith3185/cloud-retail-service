//package com.company.invoiceservice.viewmodel;
//
//import com.company.invoiceservice.model.Invoice;
//import com.company.invoiceservice.model.InvoiceItem;
//
//import java.util.List;
//import java.util.Objects;
//
//public class InvoiceViewModel extends Invoice {
//
//    private List<InvoiceItem> invoiceItems;
//
//
//    public List<InvoiceItem> getInvoiceItems() {
//        return invoiceItems;
//    }
//
//    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
//        this.invoiceItems = invoiceItems;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        InvoiceViewModel that = (InvoiceViewModel) o;
//        return invoiceItems.equals(that.invoiceItems);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), invoiceItems);
//    }
//
//    // to string
//
//
//    @Override
//    public String toString() {
//        return "InvoiceViewModel{" +
//                "invoiceItems=" + invoiceItems +
//                '}';
//    }
//}
//
//

