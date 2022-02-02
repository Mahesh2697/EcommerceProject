package com.AccountService.Account.repository;

import com.AccountService.Account.entity.InventoryEntity;
import com.AccountService.Account.entity.SkusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository  extends JpaRepository<InventoryEntity, String> {

    InventoryEntity findBySkusEntities(SkusEntity sku);
}
