package com.shop.coupon.main.service.impl;

import com.shop.coupon.main.entity.Coupon;
import com.shop.coupon.main.exception.CouponNotFoundException;
import com.shop.coupon.main.repo.CouponRepo;
import com.shop.coupon.main.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private CouponRepo couponRepo;

    private static final Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);


    @Override
    public Coupon createCoupon(Coupon coupon) {
        logger.info("Creating coupon: {}", coupon);
        coupon.setCouponCode(generateUniqueCouponCode());
        Coupon createdCoupon = couponRepo.save(coupon);
        logger.info("Coupon created: {}", createdCoupon);
        return createdCoupon;
    }
    public String generateUniqueCouponCode() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    @Override
    public List<Coupon> getAllCoupons() {
        logger.info("Fetching all coupons");
        List<Coupon> coupons = couponRepo.findAll();
        logger.info("Fetched {} coupons", coupons.size());
        return coupons;
    }

    @Override
    public Coupon getCouponByCode(String couponCode) {
        return couponRepo.findByCouponCode(couponCode);
    }
}
