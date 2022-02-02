package com.AccountService.Account.entity;


import javax.persistence.*;

@Table
@Entity(name="shipping")
public class ShippingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    @Column
    private String houseNo;
    @Column
    private String landMark;
    @Column
    private String pinCode;
    @Column
    private String city;
    @Column
    private String state;
    @OneToOne(cascade = CascadeType.ALL)
    private OrderEntity orderEntity;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }

    @Override
    public String toString() {
        return "ShippingEntity{" +
                "addressId=" + addressId +
                ", houseNo='" + houseNo + '\'' +
                ", landMark='" + landMark + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", orderEntity=" + orderEntity +
                '}';
    }
}