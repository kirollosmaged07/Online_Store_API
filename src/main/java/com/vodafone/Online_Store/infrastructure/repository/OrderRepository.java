package com.vodafone.Online_Store.infrastructure.repository;

import com.vodafone.Online_Store.core.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
