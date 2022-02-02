package com.AccountService.Account.service;

import com.AccountService.Account.entity.OrderEntity;
import com.AccountService.Account.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FulfilmentService {

    @Autowired
    private OrderRepository orderRepository;

    //Here this method will update once the order is received
    public String updateItemStatus(Long orderId, String orderStatus) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if (orderEntity != null) {
            orderEntity.setOrderStatus(orderStatus);
            orderRepository.save(orderEntity);
        }
        return  "Order Id:"+orderEntity.getOrderId()+"\n"+"updated status:"+" "+orderEntity.getOrderStatus();
    }
}
