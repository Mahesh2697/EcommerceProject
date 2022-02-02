package com.AccountService.Account.repository;

import com.AccountService.Account.entity.CustomerRegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerRegisterEntity, String> {

    CustomerRegisterEntity findByEmail(String email);
}
