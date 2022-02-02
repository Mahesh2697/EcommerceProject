package com.AccountService.Account.repository;

import com.AccountService.Account.entity.SkusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkusRepository extends JpaRepository<SkusEntity , String> {
    SkusEntity findBySkuCode(String SkuCode);
}
