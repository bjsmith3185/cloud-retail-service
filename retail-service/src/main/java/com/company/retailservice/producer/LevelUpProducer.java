package com.company.retailservice.producer;

import com.company.retailservice.dto.LevelUp;
import com.company.retailservice.dto.OrderRequestView;
import com.company.retailservice.dto.OrderResponseView;
import com.company.retailservice.service.RetailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LevelUpProducer {
    public static final String EXCHANGE = "level-up-exchange";
    public static final String ROUTING_KEY = "level.#";
//    public static final String SAVE_QUEUED_MSG = "Point(s) queued for save";
//    public static final String UPDATE_QUEUED_MSG = "Point(s) queued for update";

//    @Autowired
//    RetailService service;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public LevelUpProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

//    @RequestMapping(value = "/levelup", method = RequestMethod.POST)
//    public String createLevelUp(@RequestBody @Valid LevelUp levelUp) {
//        LevelUp msg = new LevelUp(levelUp.getId(), levelUp.getCustomerId(), levelUp.getPoints(), levelUp.getMemberDate());
//        System.out.println("Sending points...");
//        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
////        System.out.println("Point(s) Sent");
////        return SAVE_QUEUED_MSG;
//        return "points sent to que";
//    }

//    @PutMapping(value = "/levelup/{id}")
//    public String updateLevelUp(@RequestBody LevelUp levelUp) {
//
//        LevelUp msg = new LevelUp(levelUp.getId(), levelUp.getCustomerId(), levelUp.getPoints(), levelUp.getMemberDate());
//        System.out.println("Sending point(s)...");
//        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, msg);
//        System.out.println("Point(s) update sent");
//
//        return UPDATE_QUEUED_MSG;
//    }
//
//    @RequestMapping(value = "/retail/order", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    public OrderResponseView createOrder(@RequestBody OrderRequestView orderRequestView) {
//
//
//        return service.createOrder(orderRequestView);
//
//    }


    public void toLevelUpQueue(LevelUp levelUp) {

        System.out.println("in the queue producer");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, levelUp);
        System.out.println("level up sent to queue");
    }






}
