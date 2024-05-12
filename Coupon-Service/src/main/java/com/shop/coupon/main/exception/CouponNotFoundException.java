package com.shop.coupon.main.exception;

public class CouponNotFoundException extends RuntimeException{

    public CouponNotFoundException(String message) {
        super(message);
    }
}
