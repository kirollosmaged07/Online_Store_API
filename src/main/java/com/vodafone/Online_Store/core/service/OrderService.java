package com.vodafone.Online_Store.core.service;

import com.vodafone.Online_Store.core.domain.Order;
import com.vodafone.Online_Store.core.domain.OrderRequest;

import java.util.List;

public interface OrderService {

    Order placeOrder(OrderRequest orderRequest);

    List<Order> getOrderHistory(Long userId);

    List<Order> getAllOrders();

    Order updateOrderStatus(Long orderId, String status);
}
