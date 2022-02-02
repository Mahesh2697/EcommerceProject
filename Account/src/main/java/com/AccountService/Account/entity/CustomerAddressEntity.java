package com.AccountService.Account.entity;
import javax.persistence.*;

@Entity
@Table(name="address_details")
public class CustomerAddressEntity {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
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
    @Column
    private Boolean shippingAddress;
    @Column
    private Boolean  billingAddress;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_register_entity_email")
    private CustomerRegisterEntity customerRegisterEntity;

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

    public Boolean getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Boolean shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Boolean getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Boolean billingAddress) {
        this.billingAddress = billingAddress;
    }

    public CustomerRegisterEntity getCustomerRegisterEntity() {
        return customerRegisterEntity;
    }

    public void setCustomerRegisterEntity(CustomerRegisterEntity customerRegisterEntity) {
        this.customerRegisterEntity = customerRegisterEntity;
    }
    @Override
    public String toString() {
        return "CustomerAddressEntity{" +
                "houseNo='" + houseNo + '\'' +
                ", landMark='" + landMark + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                ", customerRegisterEntity=" + customerRegisterEntity +
                '}';
    }
}
