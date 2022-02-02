package com.AccountService.Account.repository;


import com.AccountService.Account.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findByOrderId(Long orderId);
}
