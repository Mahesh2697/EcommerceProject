package com.AccountService.Account.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product_details")
public class ProductEntity {

    @Id
    private String productCode;
    @Column
    private String productName;
    @Column
    private String description;
    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<SkusEntity> skuEntityList;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SkusEntity> getSkuEntityList() {
        return skuEntityList;
    }

    public void setSkuEntityList(List<SkusEntity> skuEntityList) {
        this.skuEntityList = skuEntityList;
    }
}
