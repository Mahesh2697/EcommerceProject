package com.AccountService.Account.model;
public class ShippingModel {
    private String houseNo;
    private String landMark;
    private String pinCode;
    private String city;
    private String state;
    private OrderModel orderModel;

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

    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    @Override
    public String toString() {
        return "ShippingModel{" +
                "houseNo='" + houseNo + '\'' +
                ", landMark='" + landMark + '\'' +
                ", pinCode='" + pinCode + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", orderModel=" + orderModel +
                '}';
    }
}