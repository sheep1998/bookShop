package com.example.bookshop.Service;

import com.example.bookshop.Model.Ordermenu;

import java.util.List;

public interface OrdermenuService {
    List<Ordermenu> queryOrder(int user_id);
}
