package com.AccountService.Account.controller;

import com.AccountService.Account.model.InventoryModel;
import com.AccountService.Account.model.PriceModel;
import com.AccountService.Account.model.ProductModel;
import com.AccountService.Account.model.SkusModel;
import com.AccountService.Account.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping("/add-products")
    public  void addProduct(@RequestBody ProductModel productModel){
        productService.addProduct(productModel);
    }


    @PostMapping("/add-skus")
    public void addSkus(@RequestHeader String productCode, @RequestBody SkusModel skusModel){
         productService.addSkus(productCode, skusModel);
    }


    @PostMapping("/add-price")
    public void addPrice(@RequestHeader String skuCode, @RequestBody PriceModel priceModel){
         productService.addPrice(skuCode, priceModel);
    }

    @PostMapping("/add-stock")
    public String addStock(@RequestHeader String skuCode, @RequestBody InventoryModel inventoryModel){
        return productService.addStock(skuCode, inventoryModel);
    }
    @GetMapping("/get-all-products")
    public List<ProductModel> getAllProducts(){
        return productService.getProducts();
    }


}
