package com.AccountService.Account.model;


public class OrderModel {
    private String orderStatus;
    private CustomerRegisterModel customerRegisterModel;
    private ShippingModel shippingModel;


    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public CustomerRegisterModel getCustomerRegisterModel() {
        return customerRegisterModel;
    }

    public void setCustomerRegisterModel(CustomerRegisterModel customerRegisterModel) {
        this.customerRegisterModel = customerRegisterModel;
    }

    public ShippingModel getShippingModel() {
        return shippingModel;
    }

    public void setShippingModel(ShippingModel shippingModel) {
        this.shippingModel = shippingModel;
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderStatus='" + orderStatus + '\'' +
                ", customerRegisterModel=" + customerRegisterModel +
                ", shippingModel=" + shippingModel +
                '}';
    }
}