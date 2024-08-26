package com.vodafone.Online_Store.web.controller;

import com.vodafone.Online_Store.core.domain.Order;
import com.vodafone.Online_Store.core.domain.OrderRequest;
import com.vodafone.Online_Store.core.domain.UserInfo;
import com.vodafone.Online_Store.core.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody OrderRequest orderRequest) {
        Order order = orderService.placeOrder(orderRequest);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrderHistory(@RequestParam(required = false) Long userId) {
        if(userId == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(authority -> authority.equals("ADMIN"))) {
                List<Order> orders = orderService.getAllOrders();
                return ResponseEntity.ok(orders);
            }
        }
        List<Order> orders = orderService.getOrderHistory(userId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long orderId, @RequestParam String status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).anyMatch(authority -> authority.equals("ADMIN"))) {
            Order order = orderService.updateOrderStatus(orderId, status);
            return ResponseEntity.ok(order);
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
}
