package com.AccountService.Account.repository;

import com.AccountService.Account.entity.CustomerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity , Long> {
}
