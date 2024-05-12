package com.shop.coupon.main.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="tbl_coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="coupon_id")
    private int id;

    @Column(name="couponcode")
    private String couponCode;

    @Column(name="coupon_discount")
    private int discount;

    @Column(name="coupon_expiry_date")
    private LocalDate expirationDate;

    public Coupon() {
    }

    public Coupon(int id, String couponCode, int discount, LocalDate expirationDate) {
        this.id = id;
        this.couponCode = couponCode;
        this.discount = discount;
        this.expirationDate = expirationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
