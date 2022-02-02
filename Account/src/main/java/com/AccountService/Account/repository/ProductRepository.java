package com.AccountService.Account.repository;

import com.AccountService.Account.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity , String> {
        ProductEntity findByProductCode(String productCode);
}
