package com.AccountService.Account.model;

public class CustomerAddressModel {


    private String houseNo;
    private String landMark;
    private String pinCode;
    private String city;
    private String state;
    private Boolean shippingAddress;
    private Boolean  billingAddress;

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

    @Override
    public String toString() {
        return "CustomerAddressModel{" +
                "houseNo='" + houseNo + '\'' +
                ", landMark='" + landMark + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", shippingAddress=" + shippingAddress +
                ", billingAddress=" + billingAddress +
                '}';
    }
}
