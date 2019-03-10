package com.example.bookshop.Service.ServiceImpl;

import com.example.bookshop.DAO.OrdermenuRepository;
import com.example.bookshop.Model.Ordermenu;
import com.example.bookshop.Service.OrdermenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdermenuServiceImpl implements OrdermenuService{
    @Autowired
    private OrdermenuRepository ordermenuRepository;

    @Override
    public List<Ordermenu> queryOrder(int user_id) {
        return ordermenuRepository.findALLByUserId(user_id);
    }
}
