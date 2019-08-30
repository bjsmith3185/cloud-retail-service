package com.company.retailservice.service;

import com.company.retailservice.dto.*;
import com.company.retailservice.feign.*;
import com.company.retailservice.producer.LevelUpProducer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Component
public class RetailService {

    // Dependencies
    @Autowired
    private LevelUpProducer que;

    @Autowired
    private ProductServiceClient productServiceClient;
    @Autowired
    private CustomerServiceClient customerServiceClient;
    @Autowired
    private InventoryServiceClient inventoryServiceClient;
    @Autowired
    private LevelUpServiceClient levelUpServiceClient;
    @Autowired
    private InvoiceServiceClient invoiceServiceClient;

    public RetailService(LevelUpProducer que, ProductServiceClient productServiceClient, CustomerServiceClient customerServiceClient, InventoryServiceClient inventoryServiceClient,
                         LevelUpServiceClient client, InvoiceServiceClient invoiceServiceClient) {
        this.levelUpServiceClient = client;
        this.que = que;
        this.productServiceClient = productServiceClient;
        this.customerServiceClient = customerServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.invoiceServiceClient = invoiceServiceClient;

    }


    // Service Layer public methods:


    public Product getByProductId(int id) {

        return productServiceClient.getProductById(id);
    }

    public List<Product> getAllProducts() {

        return productServiceClient.getAllProducts();
    }


    public OrderResponseView createOrder(OrderRequestView orderRequestView) {

        System.out.println();
        System.out.println("In the serviceLayer");
        System.out.println(orderRequestView.toString());
        System.out.println();


        // ******* may need to add the product listPrice in the request view  *********

        // check if the products are in stock
        List<InStockProducts> inStockProducts = checkIfInStock(orderRequestView.getProducts());

        if (inStockProducts.size() == 0) {
            // all products ordered are out of stock
            // maybe throw execption "out of stock"
            return null;
        }

        System.out.println();
        System.out.println("!!!!!!!!!!!!!!!!!!!1  IN STOCK PRODUCTS ");

        System.out.println(inStockProducts.toString());
        System.out.println();


        //-----------------


        // Check if the customer exists or needs to be created
        Customer customer = checkCustomer(orderRequestView.getCustomer());
        System.out.println("!!!!!!!!!!!!!!!!");
        System.out.println(customer.toString());
        System.out.println();


        // create a ProductInvoice object for each item ( new )
        // add the product object
        List<ProductInvoice> productInvoices = addProductInfo(inStockProducts, customer.getId());

        System.out.println();
        System.out.println("!!!!!!!!!!!!!!!!  ADDING PRODUCT OBJECT TO THE LIST ");
        System.out.println(productInvoices.toString());
        System.out.println();


        // create and add Invoice and Invoice Item to each ProductInvoice object
        productInvoices = createInvoice(productInvoices);
        System.out.println(productInvoices.toString());
        System.out.println();

        System.out.println();
        System.out.println("!!!!!!!!!!!!!!!!  ADDING INVOICE AND INVOICEiTEM OBJECTS TO THE LIST");
        System.out.println(productInvoices.toString());
        System.out.println();


        // need to calculate the total price for each product
        productInvoices = totalPriceForEachProduct(productInvoices);
        System.out.println();
        System.out.println();
        System.out.println("!!!!!!!!!!!!!!!! CALCULATING TOTAL PRICE FOR EACH PRODUCCT ");
        System.out.println(productInvoices.toString());
        System.out.println();


        // Now create a singleInvoice object to begin populating
        SingleInvoice singleInvoice = createSingleInvoice(productInvoices);
        System.out.println();
        System.out.println();
        System.out.println("!!!!!!!!!!!!!!!! CREATING SINGLEINVOICE OBJECT   ");
        System.out.println(singleInvoice.toString());
        System.out.println();


        // trying to use the circuit breaker methods here
        List<LevelUp> tempLevelUpList = levelUpPoints(customer.getId());


        System.out.println();
        System.out.println();
        System.out.println(" @@@@@  this is the level up list ");
        System.out.println(tempLevelUpList.toString());

        // create LevelUpInfo object
        LevelUpInfo levelUpInfo = createLevelUp(customer.getId(), singleInvoice.getOrderTotalPrice(), tempLevelUpList);
        System.out.println();
        System.out.println();
        System.out.println("!!!!!!!!!!!!!!!! CREATING LEVELUPINFO OBJECT  ");
        System.out.println(levelUpInfo.toString());
        System.out.println();
        System.out.println();


        //      create OrderResponseView object
        return createResponseView(singleInvoice, customer, levelUpInfo);
//       return null;
    }


    // Helper Methods



    public List<InStockProducts> checkIfInStock(List<InputItem> itemList) {
        // loop the list of products to see if they are in stock
        // if they are available put them in a temp list

        System.out.println();
        System.out.println();
        System.out.println("!!!!!!!!!!!!!!  THIS IS THE ITEM LIST TO CHECK INVENTORY ON");
        System.out.println(itemList.toString());


        List<InStockProducts> inStockProducts = new ArrayList<>();

        // Create a List<Inventory> to popoulate with the updated qty of instock products
        List<Inventory> updatedInventoryList = new ArrayList<>();


        for (InputItem each : itemList) {

            // get Inventory object for each productId
            Inventory tempInventory = inventoryServiceClient.getInventoryByProductId(each.getProductId());

            System.out.println("This is the returned inventory object for product");
            System.out.println(tempInventory.toString());
            System.out.println(tempInventory.getId());

            //  *** the inventoryId is coming back as 0.

            if (each.getQuantity() <= tempInventory.getQuantity()) {

                InStockProducts tempInStock = new InStockProducts();
                tempInStock.setProductId(each.getProductId());
                tempInStock.setQuantity(each.getQuantity());
                tempInStock.setInventory(tempInventory);

                inStockProducts.add(tempInStock);

                // Also, create a temp Inventory object to update the qty
                Inventory updatedInventory = new Inventory();
                updatedInventory.setId(tempInventory.getId());
                updatedInventory.setProductId(each.getProductId());
                updatedInventory.setQuantity(tempInventory.getQuantity() - each.getQuantity());
                // add this temp Inventory to the updatedInventoryList
                updatedInventoryList.add(updatedInventory);
            }

        }

        // Send a List back to Inventory service to update the stock qty's
        if (updatedInventoryList.size() > 0) {
            updateInventory(updatedInventoryList);
        }

        return inStockProducts;
    }

    public Customer checkCustomer(Customer customer) {

        if (customer.getId() <= 0) {
            // create a new Customer
            System.out.println("CREATING A NEW CUSTOMER OBJECT FROM CUSTOMER-SERVICE");
            customer = customerServiceClient.createCustomer(customer);
        }

        return customer;
    }


    public OrderResponseView createResponseView(SingleInvoice singleInvoice, Customer customer, LevelUpInfo levelUpInfo) {
        // Create an OrderResponseView to return
        OrderResponseView returnView = new OrderResponseView();
        returnView.setCustomer(customer);
        returnView.setOrder(singleInvoice);
        returnView.setLevelUpInfo(levelUpInfo);

        return returnView;
    }


    public List<LevelUp> levelUpPoints(int id) {

        return levelUpServiceClient.getLevelUpByCustomer(id);
    }


    public LevelUpInfo createLevelUp(int customerId, BigDecimal totalPrice, List<LevelUp> leveluplist) {

        // find the total from the leveluplist parameter
        // loop the list and add up the existing points
        int pointHistoryTotal = 0;

        for (LevelUp each : leveluplist) {
            pointHistoryTotal += each.getPoints();
        }

        System.out.println();
        System.out.println(" this is the points total ");
        System.out.println(pointHistoryTotal);
        System.out.println();


        // Calculate the current level up points from the current order
        int factor = BigDecimal.valueOf(totalPrice.longValue())
                .divide(BigDecimal.valueOf(50)).intValue();
        // use this int to calculate the total points
        int newPoints = factor * 10;


        // Create a LevelUpInfo object that will be returned
        LevelUpInfo levelUpInfo = new LevelUpInfo();
        // add the current points to the return object
        levelUpInfo.setCurrentOrderPoints(newPoints);
        // set the total history point to the return object
        levelUpInfo.setTotalPoints(pointHistoryTotal + newPoints);


        System.out.println();
        System.out.println("see shat is here for level up    ");
        System.out.println();
        System.out.println(levelUpInfo.toString());


        // send the points for this order to Levelup-Service
        // this will use the levelup-que
        LevelUp currentLevelUp = new LevelUp();
        currentLevelUp.setPoints(newPoints);
        currentLevelUp.setCustomerId(customerId);
        currentLevelUp.setMemberDate(LocalDate.now());


        que.toLevelUpQueue(currentLevelUp);


//        levelUpServiceClient.createLevelUp(currentLevelUp);

        return levelUpInfo;
    }


    public SingleInvoice createSingleInvoice(List<ProductInvoice> productInvoices) {

        // get the first index of the list to get common data to populate
        ProductInvoice commonData = productInvoices.get(0);

        // loop the productInvoices list to create OrderProducts objects
        // List to hold all the products for this invoice
        List<OrderProducts> productList = new ArrayList<>();


        for (ProductInvoice each : productInvoices) {
            OrderProducts orderProducts = new OrderProducts();
            orderProducts.setInvoiceItemId(each.getInvoiceItem().getId());
            orderProducts.setQuantity(each.getQuantity());
            orderProducts.setListPrice(each.getProduct().getListPrice());
            orderProducts.setTotalPrice(each.getTotalPrice());
            orderProducts.setProductName(each.getProduct().getProductName());
            orderProducts.setProductDescription(each.getProduct().getProductDescription());

            productList.add(orderProducts);
        }

        // start to populate the singleInvoice object
        SingleInvoice singleInvoice = new SingleInvoice();
        singleInvoice.setInvoiceId(commonData.getInvoiceId());
        // call method of calcualte order total
        singleInvoice.setOrderTotalPrice(orderTotalPrice(productList));
        singleInvoice.setOrderItems(productList);

        // so, singleInvoice object is created

        return singleInvoice;
    }

    public BigDecimal orderTotalPrice(List<OrderProducts> productList) {
        BigDecimal orderTotal = new BigDecimal(0.00).setScale(2);

        for (OrderProducts each : productList) {
            orderTotal = orderTotal.add(each.getTotalPrice());
        }

        return orderTotal;
    }


    public List<ProductInvoice> totalPriceForEachProduct(List<ProductInvoice> productInvoices) {

//        BigDecimal orderTotal = new BigDecimal(0.00).setScale(2);

        // need to find where the quantity and list price are in the object
        for (ProductInvoice each : productInvoices) {
            BigDecimal productTotal = new BigDecimal(0.00).setScale(2);
            BigDecimal tempQuantity = new BigDecimal(each.getQuantity());
            BigDecimal productPrice = each.getProduct().getListPrice();

            productTotal = tempQuantity.multiply(productPrice);

            each.setTotalPrice(productTotal);
        }

        return productInvoices;
    }


    public List<ProductInvoice> addProductInfo(List<InStockProducts> inStockProduct, int customerId) {
        List<ProductInvoice> productInvoiceList = new ArrayList<>();

        for (InStockProducts each : inStockProduct) {
            ProductInvoice productInvoice = new ProductInvoice();
            productInvoice.setCustomerId(customerId);
            // call service for product info
            productInvoice.setProduct(productServiceClient.getProductById(each.getProductId()));
            productInvoice.setInventory(each.getInventory());
            productInvoice.setQuantity(each.getQuantity());

            // add this object to the return list
            productInvoiceList.add(productInvoice);

        }
        return productInvoiceList;
    }

    public List<ProductInvoice> createInvoice(List<ProductInvoice> inStockProduct) {

        // List of InvoiceItems to populate
        List<InvoiceItem> invoiceItemsList = new ArrayList<>();
        // loop the list and send a create invoice for each
        for (ProductInvoice each : inStockProduct) {
            //temp InvoiceItem to populate
            InvoiceItem invoiceItem = new InvoiceItem();

            invoiceItem.setInventoryId(each.getInventory().getId());
            invoiceItem.setQuantity(each.getQuantity());
            invoiceItem.setUnitPrice(each.getProduct().getListPrice());

            invoiceItemsList.add(invoiceItem);
        }

        // temp InvoiceViewModel to populate
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();

        // temp Invoice to populate
        Invoice invoice = new Invoice();
        invoice.setCustomerId(inStockProduct.get(0).getCustomerId());
        invoice.setPurchaseDate(LocalDate.now());


        invoiceViewModel.setInvoiceItems(invoiceItemsList);
        invoiceViewModel.setInvoice(invoice);

        InvoiceViewModel fromService = invoiceServiceClient.createInvoice(invoiceViewModel);

        // add the data from the invoice to the productInvoice object

        for (ProductInvoice eachProductInvoice : inStockProduct) {

            for (InvoiceItem eachInvoiceItem : fromService.getInvoiceItems()) {

                if (eachProductInvoice.getInventory().getId() == eachInvoiceItem.getInventoryId()) {

                    System.out.println();
                    System.out.println();
                    System.out.println("!!!!!!!!!!11");
                    System.out.println(eachInvoiceItem.toString());

                    // inventoryId's match so set the invoiceItem data
                    eachProductInvoice.setInvoiceItem(eachInvoiceItem);
                    eachProductInvoice.setInvoiceId(eachInvoiceItem.getInvoiceId());
                }
            }

        }

        return inStockProduct;
    }





    public void updateInventory(List<Inventory> list) {
        for (Inventory each : list) {
            inventoryServiceClient.updateInventory(each.getId(), each);
        }
    }





}
