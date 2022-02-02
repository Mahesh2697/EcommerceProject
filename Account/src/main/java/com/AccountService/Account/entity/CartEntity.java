package com.AccountService.Account.entity;


import javax.persistence.*;

@Entity
@Table(name="cart_details")
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cartId;
    @Column
    private String productCode;
    @Column
    private String skuCode;
    @Column
    private String quantity;
    @ManyToOne
    @JoinColumn(name = "customer_register_entity_email")
    private CustomerRegisterEntity customerRegisterEntity;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public CustomerRegisterEntity getCustomerRegisterEntity() {
        return customerRegisterEntity;
    }

    public void setCustomerRegisterEntity(CustomerRegisterEntity customerRegisterEntity) {
        this.customerRegisterEntity = customerRegisterEntity;
    }
}

