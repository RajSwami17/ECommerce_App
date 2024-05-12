package com.shop.coupon.main.controller;

import com.shop.coupon.main.entity.Coupon;
import com.shop.coupon.main.exception.CouponNotFoundException;
import com.shop.coupon.main.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/create")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon)
    {
        Coupon createCoupon = couponService.createCoupon(coupon);
        return new ResponseEntity<>(createCoupon, HttpStatus.CREATED);//201 Created
    }

    @GetMapping("/{couponCode}")
    public ResponseEntity<?> getCouponByCode(@PathVariable("couponCode") String couponCode) {
        Coupon coupon = couponService.getCouponByCode(couponCode);
        if(coupon==null){
            throw new CouponNotFoundException("Coupon Not Found With Id:" + couponCode);
        }
        return new ResponseEntity<>(coupon,HttpStatus.OK);
    }

    @GetMapping("/getallcoupons")
    public ResponseEntity<List<Coupon>> getAllCoupons()
    {
        List<Coupon> coupons = couponService.getAllCoupons();
        if(coupons.isEmpty()){
            return new ResponseEntity<>(coupons, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(couponService.getAllCoupons(), HttpStatus.OK);
    }

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<Object> handleCouponNotFound(CouponNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); //404 Not Found
    }

}
