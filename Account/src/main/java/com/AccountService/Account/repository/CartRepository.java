package com.AccountService.Account.repository;

import com.AccountService.Account.entity.CartEntity;
import com.AccountService.Account.entity.CustomerRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
    List<CartEntity> findByCustomerRegisterEntity(CustomerRegisterEntity Email);
}

