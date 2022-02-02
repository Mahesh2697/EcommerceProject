package com.AccountService.Account.entity;


import javax.persistence.*;

@Entity
@Table(name="skus_details")
public class SkusEntity {
    @Id
    private String skuCode;
    @Column
    private String size;
    @ManyToOne
    @JoinColumn(name = "product_entity_product_code")
    private ProductEntity productEntity;
    @OneToOne(mappedBy = "skusEntity", cascade=CascadeType.ALL)
    private PriceEntity priceEntity;
    @OneToOne(mappedBy = "skusEntities", cascade=CascadeType.ALL)
    private InventoryEntity inventoryEntity;


    public InventoryEntity getInventoryEntity() {
        return inventoryEntity;
    }

    public void setInventoryEntity(InventoryEntity inventoryEntity) {
        this.inventoryEntity = inventoryEntity;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }

    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    public PriceEntity getPriceEntity() {
        return priceEntity;
    }

    public void setPriceEntity(PriceEntity priceEntity) {
        this.priceEntity = priceEntity;
    }

}
