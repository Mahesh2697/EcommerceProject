package com.AccountService.Account.entity;


import javax.persistence.*;

@Entity
@Table(name="order_details")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @Column
    private String orderStatus;
    @ManyToOne
    @JoinColumn(name = "customer_register_entity_email")
    private CustomerRegisterEntity customerRegisterEntity;

    @OneToOne(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    private ShippingEntity shippingEntity;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public CustomerRegisterEntity getCustomerRegisterEntity() {
        return customerRegisterEntity;
    }

    public void setCustomerRegisterEntity(CustomerRegisterEntity customerRegisterEntity) {
        this.customerRegisterEntity = customerRegisterEntity;
    }

    public ShippingEntity getShippingEntity() {
        return shippingEntity;
    }

    public void setShippingEntity(ShippingEntity shippingEntity) {
        this.shippingEntity = shippingEntity;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderId=" + orderId +
                ", orderStatus='" + orderStatus + '\'' +
                ", customerRegisterEntity=" + customerRegisterEntity +
                ", shippingEntity=" + shippingEntity +
                '}';
    }
}