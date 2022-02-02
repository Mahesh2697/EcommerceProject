package com.AccountService.Account.model;


import java.util.List;

public class CustomerRegisterModel {

    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private String password;
    private List<CustomerAddressModel> customerAddressModelList;


    public List<CustomerAddressModel> getCustomerAddressModelList() {
        return customerAddressModelList;
    }

    public void setCustomerAddressModelList(List<CustomerAddressModel> customerAddressModelList) {
        this.customerAddressModelList = customerAddressModelList;
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
        return "CustomerRegisterModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", password='" + password + '\'' +
                ", customerAddressModelList=" + customerAddressModelList +
                '}';
    }
}
