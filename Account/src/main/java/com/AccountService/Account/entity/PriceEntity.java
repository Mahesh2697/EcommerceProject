package com.AccountService.Account.entity;

import javax.persistence.*;

@Entity
@Table(name="price_details")
public class PriceEntity {
    @Id
    @Column
    private Long price;
    @Column
    private String currency;
    @OneToOne
    private SkusEntity skusEntity;


    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public SkusEntity getSkusEntity() {
        return skusEntity;
    }

    public void setSkusEntity(SkusEntity skusEntity) {
        this.skusEntity = skusEntity;
    }
}
