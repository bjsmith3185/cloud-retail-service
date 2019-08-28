//package com.company.retailservice.dto;
//
//import java.math.BigDecimal;
//import java.util.Objects;
//
//public class TempInvoiceItem {
//
//
//    private int invoiceItemId;
//    private int invoiceId;
//
//    private Product product;
//
//    private int quantity;
////    private int quantityInStock;
//    private BigDecimal productTotalPrice;
//
//
//    // getters / setters
//
//    public int getInvoiceItemId() {
//        return invoiceItemId;
//    }
//
//    public void setInvoiceItemId(int invoiceItemId) {
//        this.invoiceItemId = invoiceItemId;
//    }
//
//    public int getInvoiceId() {
//        return invoiceId;
//    }
//
//    public void setInvoiceId(int invoiceId) {
//        this.invoiceId = invoiceId;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public BigDecimal getProductTotalPrice() {
//        return productTotalPrice;
//    }
//
//    public void setProductTotalPrice(BigDecimal productTotalPrice) {
//        this.productTotalPrice = productTotalPrice;
//    }
//
//    // changed this method to calculate the total
//
//    public void setProductTotalPrice() {
//
//        this.productTotalPrice =   this.product.getListPrice().multiply(new BigDecimal(this.quantity));
//    }
//
//
//    // equals / hash
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        TempInvoiceItem that = (TempInvoiceItem) o;
//        return invoiceItemId == that.invoiceItemId &&
//                invoiceId == that.invoiceId &&
//                quantity == that.quantity &&
//                product.equals(that.product) &&
//                productTotalPrice.equals(that.productTotalPrice);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(invoiceItemId, invoiceId, product, quantity, productTotalPrice);
//    }
//
//
//    // to string
//
//
//    @Override
//    public String toString() {
//        return "TempInvoiceItem{" +
//                "invoiceItemId=" + invoiceItemId +
//                ", invoiceId=" + invoiceId +
//                ", product=" + product +
//                ", quantity=" + quantity +
//                ", productTotalPrice=" + productTotalPrice +
//                '}';
//    }
//}
