package com.shop.order.main.service;

import com.shop.order.main.entity.Order;

public interface OrderService {

    public Order placeOrder(Order order, String couponCode);

    public Order getOrderById(int id);

    public Order cancelOrder(int id);
}
