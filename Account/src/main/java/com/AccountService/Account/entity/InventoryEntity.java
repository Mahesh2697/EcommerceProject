package com.AccountService.Account.entity;

import javax.persistence.*;

@Entity
@Table(name="inventory_details")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long inventoryId;
    @Column
    private String quantityAvailable;
    @OneToOne
    private SkusEntity skusEntities;

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(String quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public SkusEntity getSkusEntities() {
        return skusEntities;
    }

    public void setSkusEntities(SkusEntity skusEntities) {
        this.skusEntities = skusEntities;
    }
}
