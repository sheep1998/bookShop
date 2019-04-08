package com.example.bookshop.Controller;

import com.example.bookshop.Model.DTL;
import com.example.bookshop.Model.Orderitem;
import com.example.bookshop.Model.Ordermenu;
import com.example.bookshop.Model.User;
import com.example.bookshop.Service.BookService;
import com.example.bookshop.Service.OrderitemService;
import com.example.bookshop.Service.OrdermenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class OrderController {
    @Autowired
    private OrdermenuService ordermenuService;

    @Autowired
    private OrderitemService orderitemService;

    @Autowired
    private BookService bookService;

    private HashMap parse_cart(HttpServletRequest request){
        HashMap cart = new HashMap();
        Iterator iter = request.getParameterMap().entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry entry = (Map.Entry) iter.next();
            String id = (String)entry.getKey();
            int num = Integer.parseInt(request.getParameter(id));
            cart.put(id,num);
        }
        return cart;
    }

    @PostMapping(value = "/order/commit")
    @ResponseBody
    public void commitOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map cart = parse_cart(req);
        HttpSession session = req.getSession();
        try {
            HashSet<Orderitem> orderitems = new HashSet<>();
            Ordermenu order = new Ordermenu();
            User user = (User) session.getAttribute("user");
            int userid = user.getUserId();
            order.setUserId(userid);
            SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = tempDate.format(new java.util.Date());
            order.setStartDate(date);
            int totalPrice = 0;
            int orderid = ordermenuService.saveOrder(order,userid,date);

            Iterator iter = cart.entrySet().iterator();
            while (iter.hasNext()){
                Map.Entry entry = (Map.Entry) iter.next();
                Orderitem item = new Orderitem();
                int bookid = Integer.parseInt((String)entry.getKey()) ;
                int booknum = (Integer)entry.getValue();
                item.setBookId(bookid);
                item.setNumber(booknum);
                int price = bookService.queryOneBook(bookid).get().getPrice();
                bookService.changeNum(bookid,booknum*-1);
                item.setTprice(price);
                totalPrice += booknum*price;
                item.setOrderId(orderid);
                orderitems.add(item);
            }
            orderitemService.saveOrderItem(orderitems);

            Ordermenu ordermenu = ordermenuService.queryOneOrder(orderid).get();
            ordermenu.setTotalPrice(totalPrice);
            ordermenuService.save(ordermenu);
        }catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(value = "/order/all")
    @ResponseBody
    public List<Ordermenu> showOrder(HttpServletRequest req){
        try{
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            int userid = user.getUserId();

            return ordermenuService.queryOrder(userid);
        }
        catch(NullPointerException e){
            return null;
        }
    }

    @PostMapping(value = "/order/detail")
    @ResponseBody
    public  List<HashMap<String,String>> showDetail(@RequestParam("orderId")int order_id){
        return orderitemService.queryItems(order_id);
    }

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value = "/order/msg")
    @ResponseBody
    public DTL getOrderMsg(){
        return restTemplate.getForObject("http://localhost:8082/dtl",DTL.class);
    }

}
