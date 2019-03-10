package com.example.sender.Controller;

import com.example.sender.Model.DTL;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderController {

    @GetMapping("/msg")
    public String getMsg(){
        return "[收货地址] 上海上海市闵行区 江川路街道 上海交通大学闵行校区 西13楼";
    }

    @GetMapping("/dtl")
    public DTL getDTL(){
        DTL dtl = new DTL();
        dtl.setId(1);
        dtl.setPosition("[收货地址] 上海上海市闵行区 江川路街道 上海交通大学闵行校区 西13楼");
        dtl.setRestTime("快递员配送中...");
        return dtl;
    }
}
