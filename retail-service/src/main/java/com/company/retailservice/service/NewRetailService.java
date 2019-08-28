//package com.company.retailservice.service;
//
//import com.company.retailservice.dto.*;
//import com.company.retailservice.feign.*;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Component
//public class NewRetailService {
//
//    // Dependencies
//
//    @Autowired
//    private ProductServiceClient productServiceClient;
//    @Autowired
//    private CustomerServiceClient customerServiceClient;
//    @Autowired
//    private InventoryServiceClient inventoryServiceClient;
//    @Autowired
//    private LevelUpServiceClient levelUpServiceClient;
//    @Autowired
//    private InvoiceServiceClient invoiceServiceClient;
//
//    public NewRetailService(LevelUpServiceClient client) {
//        this.levelUpServiceClient = client;
//    }
//
//
//    @HystrixCommand(fallbackMethod = "getLevelUpPoints")
//    public LevelUp levelUpPoints(int id) {
//
//        return levelUpServiceClient.getLevelUpByCustomer(id);
//    }
//    // constructor
//
//    // Service Layer public methods:
//
//    public LevelUp getLevelUpPoints(int customerId) {
//        return levelUpServiceClient.getLevelUpByCustomer(customerId);
//    }
//
//    public Product getByProductId(int id) {
//
//        return productServiceClient.getProductById(id);
//    }
//
//    public List<Product> getAllProducts() {
//
//        return productServiceClient.getAllProducts();
//    }
//
//
//    public OrderResponseView createOrder(OrderRequestView orderRequestView) {
//
//        // ******* may need to add the product listPrice in the request view  *********
//
//        // check if the products are in stock
//        List<InStockProducts> inStockProducts = checkIfInStock(orderRequestView.getProducts());
//
//        if (inStockProducts.size() == 0) {
//            // all products ordered are out of stock
//            // maybe throw execption "out of stock"
//            return null;
//        }
//
//
//        // Check if the customer exists or needs to be created
//        Customer customer = checkCustomer(orderRequestView.getCustomer());
//
//
//        // create a ProductInvoice object for each item ( new )
//        // add the product object
//        List<ProductInvoice> productInvoices = addProductInfo(inStockProducts,customer.getId());
//
//        // create and add Invoice and Invoice Item to each ProductInvoice object
//        productInvoices = createInvoice(productInvoices);
//
//
//        // need to calculate the total price for each product
//        productInvoices = totalPriceForEachProduct(productInvoices);
//
//        // Now create a singleInvoice object to begin populating
//        SingleInvoice singleInvoice = createSingleInvoice(productInvoices);
//
//        // create LevelUpInfo object
//        LevelUpInfo levelUpInfo = createLevelUp(customer.getId(), singleInvoice.getOrderTotalPrice() );
//
//        // create OrderResponseView object
//
//        return createResponseView(singleInvoice, customer, levelUpInfo );
//
//    }
//
//
//    // Helper Methods
//
//    public OrderResponseView createResponseView(SingleInvoice singleInvoice, Customer customer, LevelUpInfo levelUpInfo) {
//        // Create an OrderResponseView to return
//        OrderResponseView returnView = new OrderResponseView();
//        returnView.setCustomer(customer);
//        returnView.setOrder(singleInvoice);
//        returnView.setLevelUpInfo(levelUpInfo);
//
//        return returnView;
//    }
//
//
//
//
//
//    public LevelUpInfo createLevelUp(int customerId, BigDecimal totalPrice) {
//
//       // ***** need to add logic here from the planning file
//
//
//
////        LevelUp tempLevelUp = levelUpServiceClient.getLevelUpByCustomer(customerId);
////
////        // divide the totalPrice by 50
////
////        int factor = BigDecimal.valueOf(totalPrice.longValue())
////                .divide(BigDecimal.valueOf(50)).intValue();
////
////
////        // use this int to calculate the total points
////        int newPoints = factor * 10;
////
////        // add the new points to the existing points
////        int newLevelUpTotal = tempLevelUp.getPoints() + newPoints;
////        // update the new total in the tempLevelUp object
////        tempLevelUp.setPoints(newLevelUpTotal);
////
////        // send the updated object to the que
////
////
////        //if level up has ID then its update, no level up id is create
////
////
////        // --------------------------------------
////        // return the new total
//
//        LevelUpInfo levelUpInfo = new LevelUpInfo();
//
//
//
//        return levelUpInfo;
//    }
//
//
//
//
//    public SingleInvoice createSingleInvoice(List<ProductInvoice> productInvoices) {
//
//        // get the first index of the list to get common data to populate
//        ProductInvoice commonData = productInvoices.get(0);
//
//        // loop the productInvoices list to create OrderProducts objects
//            // List to hold all the products for this invoice
//            List<OrderProducts> productList = new ArrayList<>();
//
//
//        for ( ProductInvoice each: productInvoices ) {
//            OrderProducts orderProducts = new OrderProducts();
//            orderProducts.setInvoiceItemId(each.getInvoiceItem().getInvoiceItemId());
//            orderProducts.setQuantity(each.getQuantity());
//            orderProducts.setListPrice(each.getProduct().getListPrice());
//            orderProducts.setTotalPrice(each.getTotalPrice());
//            orderProducts.setProductName(each.getProduct().getProductName());
//            orderProducts.setProductDescription(each.getProduct().getProductDescription());
//
//            productList.add(orderProducts);
//        }
//
//        // start to populate the singleInvoice object
//        SingleInvoice singleInvoice = new SingleInvoice();
//        singleInvoice.setInvoiceId(commonData.getInvoiceId());
//        // call method of calcualte order total
//        singleInvoice.setOrderTotalPrice(orderTotalPrice(productList));
//        singleInvoice.setOrderItems(productList);
//
//        // so, singleInvoice object is created
//
//        return singleInvoice;
//    }
//
//    public BigDecimal orderTotalPrice(List<OrderProducts> productList) {
//     BigDecimal orderTotal = new BigDecimal(0.00).setScale(2);
//
//        for ( OrderProducts each: productList ) {
//            orderTotal = orderTotal.add(each.getTotalPrice());
//        }
//
//        return orderTotal;
//    }
//
//
//    public List<ProductInvoice> totalPriceForEachProduct(List<ProductInvoice> productInvoices) {
//
////        BigDecimal orderTotal = new BigDecimal(0.00).setScale(2);
//
//        // need to find where the quantity and list price are in the object
//        for ( ProductInvoice each: productInvoices ) {
//            BigDecimal productTotal = new BigDecimal(0.00).setScale(2);
//            BigDecimal tempQuantity = new BigDecimal(each.getQuantity() );
//            BigDecimal productPrice = each.getProduct().getListPrice();
//
//            productTotal = tempQuantity.multiply(productPrice);
//
//            each.setTotalPrice(productTotal);
//        }
//
//        return productInvoices;
//    }
//
//
//
//    public List<ProductInvoice> addProductInfo(List<InStockProducts> inStockProduct, int customerId) {
//        List<ProductInvoice> productInvoiceList = new ArrayList<>();
//
//        for (InStockProducts each : inStockProduct) {
//            ProductInvoice productInvoice = new ProductInvoice();
//            productInvoice.setCustomerId(customerId);
//            // call service for product info
//            productInvoice.setProduct(productServiceClient.getProductById(each.getProductId()));
//            productInvoice.setInventory(each.getInventory());
//            productInvoice.setQuantity(each.getQuantity());
//
//            // add this object to the return list
//            productInvoiceList.add(productInvoice);
//
//        }
//        return productInvoiceList;
//    }
//
//    public List<ProductInvoice> createInvoice(List<ProductInvoice> inStockProduct) {
//
//        // List of InvoiceItems to populate
//        List<InvoiceItem> invoiceItemsList = new ArrayList<>();
//        // loop the list and send a create invoice for each
//        for (ProductInvoice each : inStockProduct) {
//            //temp InvoiceItem to populate
//            InvoiceItem invoiceItem = new InvoiceItem();
//
//            invoiceItem.setInventoryId(each.getInventory().getInventoryId());
//            invoiceItem.setQuantity(each.getQuantity());
//            invoiceItem.setUnitPrice(each.getProduct().getListPrice());
//
//            invoiceItemsList.add(invoiceItem);
//        }
//
//        // temp InvoiceViewModel to populate
//        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
//
//        // temp Invoice to populate
//        Invoice invoice = new Invoice();
//        invoice.setCustomerId(inStockProduct.get(0).getCustomerId());
//        invoice.setPurchaseDate(LocalDate.now());
//
//
//        invoiceViewModel.setInvoiceItems(invoiceItemsList);
//        invoiceViewModel.setInvoice(invoice);
//
//        InvoiceViewModel fromService = invoiceServiceClient.createInvoice(invoiceViewModel);
//
//        // add the data from the invoice to the productInvoice object
//
//        for ( ProductInvoice eachProductInvoice : inStockProduct ) {
//
//            for ( InvoiceItem eachInvoiceItem : fromService.getInvoiceItems() ) {
//
//                if( eachProductInvoice.getInventory().getInventoryId() == eachInvoiceItem.getInventoryId() ) {
//                    // inventoryId's match so set the invoiceItem data
//                    eachProductInvoice.setInvoiceItem(eachInvoiceItem);
//                    eachProductInvoice.setInvoiceId(eachInvoiceItem.getInvoiceId());
//                }
//            }
//
//        }
//
//      return inStockProduct;
//    }
//
//
//    public List<InStockProducts> checkIfInStock(List<InputItem> itemList) {
//        // loop the list of products to see if they are in stock
//        // if they are available put them in a temp list
//
//        List<InStockProducts> inStockProducts = new ArrayList<>();
//
//        // Create a List<Inventory> to popoulate with the updated qty of instock products
//        List<Inventory> updatedInventoryList = new ArrayList<>();
//
//
//        for (InputItem each : itemList) {
//
//            // get Inventory object for each productId
//            Inventory tempInventory = inventoryServiceClient.getInventoryByProductId(each.getProductId());
//            if (each.getQuantity() <= tempInventory.getQuantity()) {
//
//                InStockProducts tempInStock = new InStockProducts();
//                tempInStock.setProductId(each.getProductId());
//                tempInStock.setQuantity(each.getQuantity());
//                tempInStock.setInventory(tempInventory);
//
//                inStockProducts.add(tempInStock);
//
//                // Also, create a temp Inventory object to update the qty
//                Inventory updatedInventory = new Inventory();
//                updatedInventory.setInventoryId(tempInventory.getInventoryId());
//                updatedInventory.setProductId(each.getProductId());
//                updatedInventory.setQuantity(tempInventory.getQuantity() - each.getQuantity());
//                // add this temp Inventory to the updatedInventoryList
//                updatedInventoryList.add(updatedInventory);
//            }
//
//        }
//
//        // Send a List back to Inventory service to update the stock qty's
//        if (updatedInventoryList.size() > 0) {
//            updateInventory(updatedInventoryList);
//        }
//
//        return inStockProducts;
//    }
//
//
//    public void updateInventory(List<Inventory> list) {
//        for (Inventory each : list) {
//            inventoryServiceClient.updateInventory(each.getInventoryId(), each);
//        }
//    }
//
//
//
//
//    public Customer checkCustomer(Customer customer) {
//
//        if (customer.getId() < 0) {
//            // create a new Customer
//            customer = customerServiceClient.createCustomer(customer);
//        }
//
//        return customer;
//    }
//
//
//
//
//
////    public int includeLevelUp(int customerId, BigDecimal totalPrice) {
////
////        LevelUp tempLevelUp = levelUpServiceClient.getLevelUpByCustomer(customerId);
////
////        // divide the totalPrice by 50
////
////        int factor = BigDecimal.valueOf(totalPrice.longValue())
////                .divide(BigDecimal.valueOf(50)).intValue();
////
////
////        // use this int to calculate the total points
////        int newPoints = factor * 10;
////
////        // add the new points to the existing points
////        int newLevelUpTotal = tempLevelUp.getPoints() + newPoints;
////        // update the new total in the tempLevelUp object
////        tempLevelUp.setPoints(newLevelUpTotal);
////
////        // send the updated object to the que
////
////
////        //if level up has ID then its update, no level up id is create
////
////
////        // --------------------------------------
////        // return the new total
////        return newLevelUpTotal;
////    }
//
//
//
//
//}
