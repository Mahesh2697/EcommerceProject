package com.AccountService.Account.controller;

import com.AccountService.Account.service.FulfilmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FulfilmentController {
    @Autowired
    FulfilmentService fulfilmentService;

    @PatchMapping("/update-item-status")
    public String updateItemStatus(@RequestHeader Long orderId, @RequestHeader String orderStatus){
        System.out.println(orderId+"  status: "+ orderStatus);
        String message = fulfilmentService.updateItemStatus(orderId, orderStatus);
        return message;

    }
}