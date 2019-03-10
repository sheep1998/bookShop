package com.example.bookshop.Controller;

import com.example.bookshop.Model.DTL;
import com.example.bookshop.Model.Orderitem;
import com.example.bookshop.Model.Ordermenu;
import com.example.bookshop.Model.User;
import com.example.bookshop.Service.OrderitemService;
import com.example.bookshop.Service.OrdermenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
public class OrderController {
    @Autowired
    private OrdermenuService ordermenuService;

    @Autowired
    private OrderitemService orderitemService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/order/all")
    @ResponseBody
    public List<Ordermenu> showOrder(HttpSession session){
        System.out.println(session.getId());

        int userid = 1;
        return ordermenuService.queryOrder(userid);
    }

    @PostMapping(value = "/order/detail")
    @ResponseBody
    public  List<HashMap<String,String>> showDetail(@RequestParam("orderId")int order_id){
        return orderitemService.queryItems(order_id);
    }

    @PostMapping(value = "/order/msg")
    @ResponseBody
    public DTL getOrderMsg(){
        return restTemplate.getForObject("http://localhost:8082/dtl",DTL.class);
    }

}
