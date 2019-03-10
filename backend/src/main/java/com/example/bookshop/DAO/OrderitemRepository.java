package com.example.bookshop.DAO;

import com.example.bookshop.Model.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderitemRepository extends JpaRepository<Orderitem,Integer> {
    List<Orderitem> findAllByOrderId(int order_id);
}
