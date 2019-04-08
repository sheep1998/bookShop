package com.example.bookshop.Service;

import com.example.bookshop.Model.Ordermenu;

import java.util.List;
import java.util.Optional;

public interface OrdermenuService {
    List<Ordermenu> queryOrder(int user_id);
    Optional<Ordermenu> queryOneOrder(int orderid);
    int saveOrder(Ordermenu ordermenu,int userid,String date);
    void save(Ordermenu ordermenu);
}
