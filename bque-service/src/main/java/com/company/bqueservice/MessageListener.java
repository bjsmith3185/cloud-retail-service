package com.company.bqueservice;

import com.company.bqueservice.feign.LevelUpServiceClient;
import com.company.bqueservice.message.LevelUp;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    @Autowired
    private final LevelUpServiceClient levelUpServiceClient;

    MessageListener(LevelUpServiceClient levelUpServiceClient) {
        this.levelUpServiceClient = levelUpServiceClient;
    }


    @RabbitListener(queues = BqueServiceApplication.QUEUE_NAME)
    public void receiveMessage(LevelUp msg) {

        System.out.println(" In the Que Listener ");
        System.out.println(msg.toString());

        levelUpServiceClient.createLevelUp(msg);

    }

}
