package com.example.bookshop.Service.ServiceImpl;

import com.example.bookshop.DAO.OrdermenuRepository;
import com.example.bookshop.Model.Ordermenu;
import com.example.bookshop.Service.OrdermenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrdermenuServiceImpl implements OrdermenuService{
    @Autowired
    private OrdermenuRepository ordermenuRepository;

    @Override
    public List<Ordermenu> queryOrder(int user_id) {
        return ordermenuRepository.findALLByUserId(user_id);
    }

    @Override
    public Optional<Ordermenu> queryOneOrder(int orderid) {
        return ordermenuRepository.findByOrderId(orderid);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int saveOrder(Ordermenu ordermenu, int userid, String date) {
        ordermenuRepository.save(ordermenu);
        Optional<Ordermenu> order = ordermenuRepository.findByUserIdAndStartDate(userid,date);
        return order.get().getOrderId();
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void save(Ordermenu ordermenu) {
        ordermenuRepository.save(ordermenu);
    }
}
