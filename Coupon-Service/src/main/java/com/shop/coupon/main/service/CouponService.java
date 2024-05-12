package com.shop.coupon.main.service;

import com.shop.coupon.main.entity.Coupon;

import java.util.List;

public interface CouponService {

    public Coupon createCoupon(Coupon coupon);
    public List<Coupon> getAllCoupons();

    public Coupon getCouponByCode(String couponCode);
}
