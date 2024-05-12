package com.shop.order.main.exception;

public class InvalidCouponException extends RuntimeException{

    public InvalidCouponException(String couponCode) {
        super(couponCode);
    }
}
