package com.example.bookshop.Controller;

import com.example.bookshop.RabbitMQ.Sendermq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @GetMapping("/getProductMsg")
    public String getProductMsg(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://localhost:8082/msg",String.class);
    }

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private Sendermq sendermq;

    @GetMapping("/sendMsgmq")
    public void sendmq(){
        sendermq.send("hello tiger");
    }
}
