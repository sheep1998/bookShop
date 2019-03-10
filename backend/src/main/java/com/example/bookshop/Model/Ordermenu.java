package com.example.bookshop.Model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Ordermenu {
    private int orderId;
    private Integer userId;
    private Integer totalPrice;
    private String startDate;
    private String orderStatus;

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "total_price")
    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "start_date")
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "order_status")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ordermenu ordermenu = (Ordermenu) o;
        return orderId == ordermenu.orderId &&
                Objects.equals(userId, ordermenu.userId) &&
                Objects.equals(totalPrice, ordermenu.totalPrice) &&
                Objects.equals(startDate, ordermenu.startDate) &&
                Objects.equals(orderStatus, ordermenu.orderStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, userId, totalPrice, startDate, orderStatus);
    }
}
