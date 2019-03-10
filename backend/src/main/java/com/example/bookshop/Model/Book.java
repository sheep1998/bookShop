package com.example.bookshop.Model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Book {
    private int coding;
    private Integer price;
    private Integer storage;
    private String imgs;
    private String name;
    private Integer monthsale;

    @Id
    @Column(name = "coding")
    public int getCoding() {
        return coding;
    }

    public void setCoding(int coding) {
        this.coding = coding;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "storage")
    public Integer getStorage() {
        return storage;
    }

    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    @Basic
    @Column(name = "imgs")
    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return coding == book.coding &&
                Objects.equals(name, book.name) &&
                Objects.equals(price, book.price) &&
                Objects.equals(storage, book.storage) &&
                Objects.equals(imgs, book.imgs) &&
                Objects.equals(monthsale, book.monthsale);
    }

    @Override
    public int hashCode() {

        return Objects.hash(coding, name, price, storage, imgs, monthsale);
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "monthsale")
    public Integer getMonthsale() {
        return monthsale;
    }

    public void setMonthsale(Integer monthsale) {
        this.monthsale = monthsale;
    }
}
