package com.shop.coupon.main.repo;

import com.shop.coupon.main.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepo extends JpaRepository<Coupon,Integer> {
    public Coupon findByCouponCode(String couponCode);
}
