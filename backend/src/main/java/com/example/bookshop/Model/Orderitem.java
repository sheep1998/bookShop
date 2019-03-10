package com.example.bookshop.Model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Orderitem {
    private int itemId;
    private Integer orderId;
    private Integer bookId;
    private Integer number;
    private Integer tprice;

    @Id
    @Column(name = "item_id")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "book_id")
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "tprice")
    public Integer getTprice() {
        return tprice;
    }

    public void setTprice(Integer tprice) {
        this.tprice = tprice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderitem orderitem = (Orderitem) o;
        return itemId == orderitem.itemId &&
                Objects.equals(orderId, orderitem.orderId) &&
                Objects.equals(bookId, orderitem.bookId) &&
                Objects.equals(number, orderitem.number) &&
                Objects.equals(tprice, orderitem.tprice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(itemId, orderId, bookId, number, tprice);
    }
}
