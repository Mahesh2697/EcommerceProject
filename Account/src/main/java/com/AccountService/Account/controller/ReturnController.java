package com.AccountService.Account.controller;

import com.AccountService.Account.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReturnController {
    @Autowired
    private ReturnService returnService;

    @PatchMapping("/return-order")
    public String returnProduct(@RequestHeader String email, @RequestHeader Long addressId ){
        String message = returnService.returnOrder(email, addressId);
        return message;
    }

}

