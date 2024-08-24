package com.vodafone.Online_Store.core.service;



import com.vodafone.Online_Store.infrastructure.repository.OrderRepository;
import com.vodafone.Online_Store.core.service.OrderService;
import com.vodafone.Online_Store.core.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public ResponseEntity<List<Order>> getByCustomerId(Long customerId) {
        List<Order> order = orderRepository.findByUserId(customerId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    public ResponseEntity<Order> addOrder(Order order) {
        orderRepository.save(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }



}
