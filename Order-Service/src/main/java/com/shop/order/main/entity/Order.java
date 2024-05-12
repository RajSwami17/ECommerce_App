package com.shop.order.main.entity;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private int id;

    @Column(name="order_item")
    private String item;

    @Column(name="order_price")
    private int price;

    @Column(name="order_status")
    @Enumerated(EnumType.STRING) // Specify String storage for enum values
    private Status status;

    @Transient
    private String couponCode;

    public enum Status {
        CONFIRMED,
        DELIVERED,
        CANCELLED
    }

    public Order() {
    }

    public Order(int id, String item, int price, Status status) {
        this.id = id;
        this.item = item;
        this.price = price;
        this.status = status;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
