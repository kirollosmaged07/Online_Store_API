package com.vodafone.Online_Store.web.controller;
import com.vodafone.Online_Store.core.domain.Order;
import com.vodafone.Online_Store.core.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("/order/{id}")
    public ResponseEntity<List<Order>> getOrderByCustomer(@PathVariable long id) {
        return orderService.getByCustomerId(id);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }
}
