package com.example.bookshop.Controller;

import com.example.bookshop.Controller.WebSocket.MyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("webSocket")
public class WebSocketController {

    @Autowired
    private MyWebSocket myWebSocket;

    @RequestMapping("/sendAll")
    public String sendAll(){
        myWebSocket.sendAllMessage("清晨起来打开窗，心情美美哒~");
        return "websocket群体发送！";
    }

    @RequestMapping("/sendOne")
    public String sendOne(){
        myWebSocket.sendOneMessage(1, "单点消息嘻嘻！");
        return "websocket单人发送";
    }
}
