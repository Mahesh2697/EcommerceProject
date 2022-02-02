package com.AccountService.Account.controller;

import com.AccountService.Account.entity.CartEntity;
import com.AccountService.Account.model.CartModel;
import com.AccountService.Account.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;
    @PostMapping("/add-product-to-cart")
    public String addProductToCart(@RequestHeader String email, @RequestBody CartModel cartModel){
        return cartService.addProductToCart(email, cartModel);
    }

    @GetMapping("/view-cart")
    public String viewCart(@RequestHeader String email, CartEntity cartEntity){
        return cartService.viewCart(email, cartEntity);
    }
    @PostMapping("/place-order")
    public String placeOrder(@RequestHeader String email, @RequestHeader Long addressId) {
        return cartService.placeOrder(email, addressId);

    }

}
