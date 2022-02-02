package com.AccountService.Account.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Table(name="registration_details")
public class CustomerRegisterEntity {

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Id
    @Email
    private String email;
    @Column
    private String mobileNo;
    @Column
    private String password;
    @OneToMany(mappedBy ="customerRegisterEntity",fetch = FetchType.EAGER,cascade = CascadeType.ALL )
    private List<CustomerAddressEntity> customerAddressEntityList;
    @OneToMany(mappedBy = "customerRegisterEntity", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CartEntity> cartEntities;
    @OneToMany(mappedBy = "", cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orderEntityList;


    public List<OrderEntity> getOrderEntityList() {
        return orderEntityList;
    }

    public void setOrderEntityList(List<OrderEntity> orderEntityList) {
        this.orderEntityList = orderEntityList;
    }

    public List<CartEntity> getCartEntities() {
        return cartEntities;
    }

    public void setCartEntities(List<CartEntity> cartEntities) {
        this.cartEntities = cartEntities;
    }

    public List<CustomerAddressEntity> getCustomerAddressEntityList() {
        return customerAddressEntityList;
    }

    public void setCustomerAddressEntityList(List<CustomerAddressEntity> customerAddressEntityList) {
        this.customerAddressEntityList = customerAddressEntityList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "CustomerRegisterEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", password='" + password + '\'' +
                ", customerAddressEntityList=" + customerAddressEntityList +
                '}';
    }
}
