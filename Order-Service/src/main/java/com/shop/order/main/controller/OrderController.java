package com.shop.order.main.controller;

import com.shop.order.main.entity.Order;
import com.shop.order.main.exception.OrderNotFoundException;
import com.shop.order.main.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order, @RequestParam(required = false) String couponCode) {
        logger.info("OrderController Place Order Method Start");
        if (couponCode != null) {
            order = orderService.placeOrder(order, couponCode);
            logger.info("OrderController Place Order Method End");
        }
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Order> checkOrderById(@PathVariable int id) {
        logger.info("Order Id:" + id);
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable int id) {
        logger.info("Cancelled Order Id:" + id);
        return new ResponseEntity<>(orderService.cancelOrder(id), HttpStatus.OK);
    }
}
