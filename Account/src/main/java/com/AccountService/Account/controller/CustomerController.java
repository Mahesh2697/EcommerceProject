package com.AccountService.Account.controller;

import com.AccountService.Account.model.CustomerAddressModel;
import com.AccountService.Account.model.CustomerRegisterModel;
import com.AccountService.Account.model.LoginModel;
import com.AccountService.Account.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @PostMapping("/register-customers")
    public String  registerCustomer( @Valid @RequestBody CustomerRegisterModel customerRegisterModel){
      return   customerService.registerCustomer(customerRegisterModel);
    }

    @PostMapping("/add-address")
    public void addCustomerAddress(@Valid @RequestBody CustomerAddressModel address, @RequestHeader String email){
         customerService.addCustomerAddress(email,address);
    }


    @GetMapping("/get-all-data")
    public List<CustomerRegisterModel> getAllData(){return  customerService.getCustomers();}


    @GetMapping("/get-customer-by-email/{email}")
    public CustomerRegisterModel getCustomerByEmail(@PathVariable String email) {

        return customerService.getCustomerByEmail(email);
    }

    @PostMapping("/login/{email}/{password}")
    public ResponseEntity<String> login(LoginModel loginModel) {return customerService.login(loginModel);
    }
}


