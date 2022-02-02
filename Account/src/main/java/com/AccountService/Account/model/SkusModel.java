package com.AccountService.Account.model;

public class SkusModel {
    private String skuCode;
    private String size;
   private PriceModel priceModel;
    private InventoryModel inventoryModel;

    public InventoryModel getInventoryModel() {
        return inventoryModel;
    }

    public void setInventoryModel(InventoryModel inventoryModel) {
        this.inventoryModel = inventoryModel;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public PriceModel getPriceModel() {
        return priceModel;
    }

    public void setPriceModel(PriceModel priceModel) {
        this.priceModel = priceModel;
    }
}
