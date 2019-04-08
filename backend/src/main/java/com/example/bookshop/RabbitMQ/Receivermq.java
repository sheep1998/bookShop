package com.example.bookshop.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receivermq {

    @RabbitListener(queues = "tigermq")
    public void listen(String str){
        System.out.println(str);
    }
}
