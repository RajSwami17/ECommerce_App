package com.shop.order.main.service.impl;

import com.shop.order.main.entity.Coupon;
import com.shop.order.main.entity.Order;
import com.shop.order.main.exception.InvalidCouponException;
import com.shop.order.main.exception.OrderNotFoundException;
import com.shop.order.main.repo.OrderRepo;
import com.shop.order.main.service.CouponClient;
import com.shop.order.main.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CouponClient couponClient;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Order placeOrder(Order order, String couponCode) {
        logger.info("Placing order: {}", order);
        Coupon coupon = couponClient.getCouponByCode(couponCode);
        logger.info("Retrieved coupon: {}", coupon);
        if(coupon==null){
            logger.error("Invalid Coupon::"+couponCode);
            throw new InvalidCouponException("Invalid Coupon: " + couponCode);
        }
        // Calculate discounted price
        int originalPrice = order.getPrice();
        int discountAmount = originalPrice * coupon.getDiscount()/100;
        int discountedPrice = originalPrice-discountAmount;
        logger.info("Discounted Price:" + discountedPrice);
        // Set discounted price to order
        order.setPrice(discountedPrice);
        order.setStatus(Order.Status.CONFIRMED);
        order.setCouponCode(coupon.getCouponCode());
        // Save order
        Order placedOrder = orderRepo.save(order);
        logger.info("Order Service Place Holder Method End");
        return placedOrder;
    }

    @Override
    public Order getOrderById(int id) {
        logger.info("Fetching order by id: {}", id);
        Order order = orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
        logger.info("Fetched order: {}", order);
        return order;
    }

    @Override
    public Order cancelOrder(int id) {
        logger.info("Cancelling order with id: {}", id);
        Order order = orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
        order.setStatus(Order.Status.CANCELLED);
        Order saveOrder = orderRepo.save(order);
        logger.info("Order cancelled successfully: {}", order);
        return saveOrder;
    }
}
