package com.example.bookshop.Service;

import com.example.bookshop.Model.Orderitem;

import java.util.HashMap;
import java.util.List;

public interface OrderitemService {
    List<HashMap<String,String>> queryItems(int order_id);
}
