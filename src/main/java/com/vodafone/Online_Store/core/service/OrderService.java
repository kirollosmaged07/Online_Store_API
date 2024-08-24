package com.vodafone.Online_Store.core.service;
import com.vodafone.Online_Store.core.domain.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    public ResponseEntity<Order> addOrder(Order order);
    public ResponseEntity<List<Order>> getByCustomerId(Long customerId);
}
