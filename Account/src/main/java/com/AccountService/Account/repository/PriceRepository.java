package com.AccountService.Account.repository;


import com.AccountService.Account.entity.PriceEntity;
import com.AccountService.Account.entity.SkusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    PriceEntity findBySkusEntity(SkusEntity sku);
}