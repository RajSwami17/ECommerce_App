package com.shop.order.main.service;

import com.shop.order.main.entity.Coupon;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Coupon-Service",url="http://localhost:8082/api/coupon/")
public interface CouponClient {
    @GetMapping("/{couponCode}")
    Coupon getCouponByCode(@PathVariable String couponCode);
}
