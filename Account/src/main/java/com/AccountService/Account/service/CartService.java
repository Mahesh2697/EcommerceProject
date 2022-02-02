package com.AccountService.Account.service;

import com.AccountService.Account.entity.*;
import com.AccountService.Account.model.*;
import com.AccountService.Account.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SkusRepository skusRepository;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private  OrderRepository orderRepository;

    //This method will add the products to the cart based on email
    //For that here if the customer,product,skus are available and
    // quantity is more than or equal to quantity available in cart then it will add product to th cart
    public String addProductToCart(String email, CartModel cartModel){
        CustomerRegisterEntity customerRegister = customerRepository.findByEmail(email);
        if (customerRegister != null) {
            ProductEntity productEntity = productRepository.findByProductCode(cartModel.getProductCode());
            if(productEntity!=null){
                SkusEntity sku = skusRepository.findBySkuCode(cartModel.getSkuCode());
                if(sku!=null){
                    InventoryEntity inventoryEntity = inventoryRepository.findBySkusEntities(sku);
                    if(Integer.parseInt(inventoryEntity.getQuantityAvailable())>=Integer.parseInt(cartModel.getQuantity())){
                        List<CartEntity> cartEntityList = new ArrayList<>();
                        CartEntity cartEntity = new CartEntity();
                        cartEntity.setProductCode(cartModel.getProductCode());
                        cartEntity.setSkuCode(cartModel.getSkuCode());
                        cartEntity.setQuantity(cartModel.getQuantity());
                        cartEntity.setCustomerRegisterEntity(customerRegister);
                        cartEntityList.add(cartEntity);
                        customerRegister.setCartEntities(cartEntityList);
                        customerRepository.save(customerRegister);
                        return "product added to the cart";
                    }
                    //here while adding products to the cart if available quantity is less
                    // and when a customer tries to add more than actual quantity available or empty it will throw a message quantity not available
                    else if(Integer.parseInt(inventoryEntity.getQuantityAvailable())<Integer.parseInt(cartModel.getQuantity())){
                        return "required quantity is not available"+"\n"+"Available Quantity:"+inventoryEntity.getQuantityAvailable();
                    }
                }
            }
        }
        //here when a customer try to place order without adding product to the cart then it throws a message add product to the cart//
        return "please add product to the cart";
    }

    //Here this method will give the all the products which are added to the cart,product details and their total cost
    public String viewCart(String email, CartEntity cartEntity) {

        List<CartEntity> products = cartRepository.findByCustomerRegisterEntity(customerRepository.findByEmail(email));
        AtomicReference<String> cartReference = new AtomicReference<>("");
        products.stream().forEach(product-> {
            CustomerRegisterEntity customerRegister = customerRepository.findByEmail(product.getCustomerRegisterEntity().getEmail());
            ProductEntity productEntity = productRepository.findByProductCode(product.getProductCode());
            SkusEntity skusEntity = skusRepository.findBySkuCode(product.getSkuCode());
            PriceEntity priceEntity = priceRepository.findBySkusEntity(skusEntity);
            cartReference.set("customer:"+customerRegister.getEmail()+"\n"+"product name : " +productEntity.getProductName()+"\n"+"price : "+priceEntity.getPrice()+"\n"
                    +"quantity : " + product.getQuantity()+"\n" +"sub total : " +priceEntity.getPrice()*Integer.parseInt(product.getQuantity()));

        });
        return cartReference.toString();
    }

    //here this method will place an order, before placing an order
    // it will check the inventory and once order placed the inventory will be updated
    //and also once order is placed it will generate an orderId with an order status "received"//
    public String  placeOrder(String email, Long addressId) {
        CustomerRegisterEntity customerRegister = customerRepository.findByEmail(email);
        List<CartEntity> cartEntityList = cartRepository.findByCustomerRegisterEntity(customerRegister);
        List<OrderEntity> orderEntityList = new ArrayList<>();

        OrderModel orderModel = new OrderModel();
        cartEntityList.stream().forEach(cart -> {
            SkusEntity skusEntity = skusRepository.findBySkuCode(cart.getSkuCode());
            InventoryEntity inventoryEntity = inventoryRepository.findBySkusEntities(skusEntity);
            if (Integer.parseInt(inventoryEntity.getQuantityAvailable()) >= Integer.parseInt(cart.getQuantity())) {
                int available = Integer.parseInt(inventoryEntity.getQuantityAvailable()) - Integer.parseInt(cart.getQuantity());
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

        orderModel.setOrderStatus("Order Received");
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
        return " order Id:"+orderEntity.getOrderId()+"\n"+"order status:"+" "+ orderEntity.getOrderStatus();


    }

    //conversion from entity to models for customer address with customer details//
    private CustomerRegisterModel getCustomer(CustomerRegisterEntity customerRegisterEntity){
        CustomerRegisterModel customerRegisterModel = new CustomerRegisterModel();
        List<CustomerAddressModel> addressModels = new ArrayList<>();
        customerRegisterEntity.getCustomerAddressEntityList().stream().forEach(addressEntity-> {
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

    //conversion from entity to models for orders //
    private OrderEntity getOrderEntity(OrderModel orderModel){
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

