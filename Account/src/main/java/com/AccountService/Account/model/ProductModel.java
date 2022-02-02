package com.AccountService.Account.model;

import java.util.List;

public class ProductModel {

    private String productCode;
    private String productName;
    private String description;
    private List<SkusModel> skusModelList;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SkusModel> getSkusModelList() {
        return skusModelList;
    }

    public void setSkusModelList(List<SkusModel> skusModelList) {
        this.skusModelList = skusModelList;
    }

}
