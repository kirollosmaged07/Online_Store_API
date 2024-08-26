package com.vodafone.Online_Store.core.service;

import com.vodafone.Online_Store.core.domain.Order;
import com.vodafone.Online_Store.core.domain.OrderItem;
import com.vodafone.Online_Store.core.domain.OrderRequest;
import com.vodafone.Online_Store.core.domain.Product;
import com.vodafone.Online_Store.core.domain.UserInfo;
import com.vodafone.Online_Store.infrastructure.repository.OrderRepository;
import com.vodafone.Online_Store.infrastructure.repository.ProductRepository;
import com.vodafone.Online_Store.infrastructure.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserInfoRepository userInfoRepository;

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        UserInfo user = userInfoRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        List<Product> products = productRepository.findAllById(Arrays.asList(orderRequest.getProductIds()));

        Order order = new Order();
        order.setUser(user);
        order.setItems(products.stream().map(product -> new OrderItem(null, product, 1)).collect(Collectors.toList()));
        order.setStatus("PLACED");

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
