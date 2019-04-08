package com.example.bookshop.Service;

import com.example.bookshop.Model.Orderitem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface OrderitemService {
    List<HashMap<String,String>> queryItems(int order_id);
    void saveOrderItem(HashSet<Orderitem> orderitems);
}
