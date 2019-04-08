package com.example.bookshop.DAO;

import com.example.bookshop.Model.Ordermenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdermenuRepository extends JpaRepository<Ordermenu,Integer> {
    List<Ordermenu> findALLByUserId(int user_id);
    Optional<Ordermenu> findByUserIdAndStartDate(int userid,String date);
    Optional<Ordermenu> findByOrderId(int orderid);
}
