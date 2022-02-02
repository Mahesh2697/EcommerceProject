package com.AccountService.Account.service;


import com.AccountService.Account.entity.*;
import com.AccountService.Account.model.CustomerAddressModel;
import com.AccountService.Account.model.CustomerRegisterModel;
import com.AccountService.Account.model.OrderModel;
import com.AccountService.Account.model.ShippingModel;
import com.AccountService.Account.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReturnService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private SkusRepository skusRepository;
    @Autowired
    private CartRepository cartRepository;

    //here this method will return an order, after returned  an order
    // it will check the inventory and once order returned the inventory will be updated
    //and also once order is returned it will give an order status "returned"//
    public String returnOrder(String email, Long addressId) {
        CustomerRegisterEntity customerRegister = customerRepository.findByEmail(email);
        List<CartEntity> cartEntityList = cartRepository.findByCustomerRegisterEntity(customerRegister);
        List<OrderEntity> orderEntityList = new ArrayList<>();

        OrderModel orderModel = new OrderModel();
        cartEntityList.stream().forEach(cart -> {
            SkusEntity skusEntity = skusRepository.findBySkuCode(cart.getSkuCode());
            InventoryEntity inventoryEntity = inventoryRepository.findBySkusEntities(skusEntity);
            if (Integer.parseInt(inventoryEntity.getQuantityAvailable()) <= Integer.parseInt(cart.getQuantity())) {
                int available = Integer.parseInt(inventoryEntity.getQuantityAvailable()) + Integer.parseInt(cart.getQuantity());
                System.out.println(available);
                inventoryEntity.setQuantityAvailable(String.valueOf(available));
                skusEntity.setInventoryEntity(inventoryEntity);
                System.out.println(inventoryEntity.getQuantityAvailable());
            }
        });

        Optional<CustomerAddressEntity> customerAddress = customerRegister.getCustomerAddressEntityList().stream()
                .filter(address -> address.getAddressId().equals(addressId)).findFirst();
        ShippingModel shippingModel = new ShippingModel();
        shippingModel.setHouseNo(customerAddress.get().getHouseNo());
        shippingModel.setLandMark(customerAddress.get().getLandMark());
        shippingModel.setCity(customerAddress.get().getCity());
        shippingModel.setState(customerAddress.get().getState());
        shippingModel.setPinCode(customerAddress.get().getPinCode());

        orderModel.setOrderStatus("Order Returned Successfully");
        orderModel.setShippingModel(shippingModel);
        CustomerRegisterModel customer = getCustomer(customerRegister);
        orderModel.setCustomerRegisterModel(customer);

        OrderEntity orderEntity = getOrderEntity(orderModel);

        OrderEntity orderEntity1 = orderRepository.findByOrderId(orderEntity.getOrderId());
        orderEntity.setCustomerRegisterEntity(customerRegister);
        orderEntityList.add(orderEntity);
        orderEntityList.add(orderEntity1);
        customerRegister.setOrderEntityList(orderEntityList);
        orderRepository.save(orderEntity);
        customerRepository.save(customerRegister);
        return  "order status:" + " " + orderEntity.getOrderStatus();


    }

    private CustomerRegisterModel getCustomer(CustomerRegisterEntity customerRegisterEntity) {
        CustomerRegisterModel customerRegisterModel = new CustomerRegisterModel();
        List<CustomerAddressModel> addressModels = new ArrayList<>();
        customerRegisterEntity.getCustomerAddressEntityList().stream().forEach(addressEntity -> {
            CustomerAddressModel customerAddressModel = new CustomerAddressModel();
            customerAddressModel.setHouseNo(addressEntity.getHouseNo());
            customerAddressModel.setLandMark(addressEntity.getLandMark());
            customerAddressModel.setCity(addressEntity.getCity());
            customerAddressModel.setState(addressEntity.getState());
            customerAddressModel.setPinCode(addressEntity.getPinCode());
            customerAddressModel.setShippingAddress(addressEntity.getShippingAddress());
            customerAddressModel.setBillingAddress(addressEntity.getBillingAddress());
            addressModels.add(customerAddressModel);
        });
        customerRegisterModel.setFirstName(customerRegisterEntity.getFirstName());
        customerRegisterModel.setLastName(customerRegisterEntity.getLastName());
        customerRegisterModel.setEmail(customerRegisterEntity.getEmail());
        customerRegisterModel.setMobileNo(customerRegisterEntity.getMobileNo());
        customerRegisterModel.setPassword(customerRegisterEntity.getPassword());
        customerRegisterModel.setCustomerAddressModelList(addressModels);
        return customerRegisterModel;
    }

    private OrderEntity getOrderEntity(OrderModel orderModel) {
        OrderEntity orderEntity = new OrderEntity();

        ShippingEntity shippingEntity = new ShippingEntity();
        shippingEntity.setHouseNo(orderModel.getShippingModel().getHouseNo());
        shippingEntity.setLandMark(orderModel.getShippingModel().getLandMark());
        shippingEntity.setCity(orderModel.getShippingModel().getCity());
        shippingEntity.setState(orderModel.getShippingModel().getState());
        shippingEntity.setPinCode(orderModel.getShippingModel().getPinCode());

        orderEntity.setOrderStatus(orderModel.getOrderStatus());
        orderEntity.setShippingEntity(shippingEntity);
        shippingEntity.setOrderEntity(orderEntity);
        return orderEntity;
    }
}
