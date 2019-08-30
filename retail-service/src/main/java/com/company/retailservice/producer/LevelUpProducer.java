package com.company.retailservice.producer;

import com.company.retailservice.dto.LevelUp;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class LevelUpProducer {
    public static final String EXCHANGE = "level-up-exchange";
    public static final String ROUTING_KEY = "level.#";


    @Autowired
    private RabbitTemplate rabbitTemplate;

    public LevelUpProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public void toLevelUpQueue(LevelUp levelUp) {

        System.out.println("in the queue producer");
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, levelUp);
        System.out.println("level up sent to queue");
    }


}
