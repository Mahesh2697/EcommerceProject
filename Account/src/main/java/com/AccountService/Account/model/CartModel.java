package com.AccountService.Account.model;


public class CartModel {
    private String productCode;
    private String skuCode;
    private String quantity;
    private CustomerRegisterModel customerRegisterModel;

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

    public CustomerRegisterModel getCustomerRegisterModel() {
        return customerRegisterModel;
    }

    public void setCustomerRegisterModel(CustomerRegisterModel customerRegisterModel) {
        this.customerRegisterModel = customerRegisterModel;
    }

    @Override
    public String toString() {
        return "CartModel{" +
                "productCode='" + productCode + '\'' +
                ", skuCode='" + skuCode + '\'' +
                ", quantity='" + quantity + '\'' +
                ", customerRegisterModel=" + customerRegisterModel +
                '}';
    }
}

