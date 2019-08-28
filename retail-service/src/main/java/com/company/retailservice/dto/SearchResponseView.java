//package com.company.retailservice.dto;
//
//import java.util.List;
//import java.util.Objects;
//
//public class SearchResponseView {
//
//    private List<Product> productList;
//
//    // getters / setters
//    public List<Product> getProductList() {
//        return productList;
//    }
//
//    public void setProductList(List<Product> productList) {
//        this.productList = productList;
//    }
//
//    // equals / hash
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        SearchResponseView that = (SearchResponseView) o;
//        return productList.equals(that.productList);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(productList);
//    }
//
//    // to string
//    @Override
//    public String toString() {
//        return "SearchResponseView{" +
//                "productList=" + productList +
//                '}';
//    }
//}
