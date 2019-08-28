package com.company.retailservice.controller;

import com.company.retailservice.dto.*;
import com.company.retailservice.service.RetailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RetailServiceController {

    @Autowired
    private RetailService service;

//    public static final String EXCHANGE = "note-exchange";
//    public static final String ROUTING_KEY = "note.#";
//    public static final String SAVE_QUEUED_MSG = "Note(s) queued for save";
//    public static final String UPDATE_QUEUED_MSG = "Note(s) queued for update";
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public RetailServiceController(RabbitTemplate rabbitTemplate) {
//        this.rabbitTemplate = rabbitTemplate;
//    }



    // Retail-Service Controller Route Methods:


    @RequestMapping(value = "/retail/products", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        System.out.println("in the service layer");
        return service.getAllProducts();
    }

    @RequestMapping(value = "/retail/products/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable int id) {
        System.out.println("in the service layer");
        return service.getByProductId(id);
    }


    @RequestMapping(value = "/retail/order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponseView createOrder(@RequestBody OrderRequestView orderRequestView) {

        return service.createOrder(orderRequestView);

    }


}
