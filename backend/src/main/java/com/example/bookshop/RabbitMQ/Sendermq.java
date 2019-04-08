package com.example.bookshop.RabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sendermq {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(String str){
        amqpTemplate.convertAndSend("tigermq",str);
    }
}
