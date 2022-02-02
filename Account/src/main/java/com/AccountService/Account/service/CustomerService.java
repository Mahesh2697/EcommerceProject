package com.AccountService.Account.service;

import com.AccountService.Account.entity.CustomerAddressEntity;
import com.AccountService.Account.entity.CustomerRegisterEntity;
import com.AccountService.Account.model.CustomerAddressModel;
import com.AccountService.Account.model.CustomerRegisterModel;
import com.AccountService.Account.model.LoginModel;
import com.AccountService.Account.repository.CustomerAddressRepository;
import com.AccountService.Account.repository.CustomerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Autowired
    private encryptDecryptPasswordService encryptDecryptPasswordService;

    //converting register model to register entity//
    public String registerCustomer(CustomerRegisterModel customerRegisterModel) {
        CustomerRegisterEntity customerRegister = customerRepository.findByEmail(customerRegisterModel.getEmail());
        if (customerRegister == null) {

            CustomerRegisterEntity customerRegister1 = new CustomerRegisterEntity();
            customerRegister1.setFirstName(customerRegisterModel.getFirstName());
            customerRegister1.setLastName(customerRegisterModel.getLastName());
            customerRegister1.setEmail(customerRegisterModel.getEmail());
            customerRegister1.setPassword(new encryptDecryptPasswordService().encryptPassword(customerRegisterModel.getPassword()));
            customerRegister1.setMobileNo(customerRegisterModel.getMobileNo());
            List<CustomerAddressEntity> addressEntityList = new ArrayList<>();
            customerRegister1.setCustomerAddressEntityList(addressEntityList);
             customerRepository.save(customerRegister1);
            System.out.println(addressEntityList);//initially this address list will be empty
             return "customer registered successfully";

        }
        return "customer already exits with this email/details please register with new mail/details";
    }

    //adding address to the  customer based on email and converting model to entity//
    public CustomerRegisterEntity addCustomerAddress(String email , CustomerAddressModel customerAddressModel) {
        CustomerRegisterEntity customerRegisters = customerRepository.findByEmail(email);
        //if customer is present adding address to the customer//
        if(customerRegisters!=null){
            List<CustomerAddressEntity> customerAddress = new ArrayList<>();
            CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
            customerAddressEntity.setHouseNo(customerAddressModel.getHouseNo());
            customerAddressEntity.setLandMark(customerAddressModel.getLandMark());
            customerAddressEntity.setPinCode(customerAddressModel.getPinCode());
            customerAddressEntity.setCity(customerAddressModel.getCity());
            customerAddressEntity.setState(customerAddressModel.getState());
            customerAddressEntity.setBillingAddress(customerAddressModel.getBillingAddress());
            customerAddressEntity.setShippingAddress(customerAddressModel.getShippingAddress());
            customerAddressEntity.setCustomerRegisterEntity(customerRegisters);
            customerAddress.add(customerAddressEntity);
            customerRegisters.setCustomerAddressEntityList(customerAddress);
            return customerRepository.save(customerRegisters);

        }
        return null;
    }


    //converting entity to model to get all the data//
    private CustomerRegisterModel getAllData(CustomerRegisterEntity customerRegisterEntity){
        CustomerRegisterModel customerRegisterModel = new CustomerRegisterModel();
        customerRegisterModel.setFirstName(customerRegisterEntity.getFirstName());
        customerRegisterModel.setLastName(customerRegisterEntity.getLastName());
        customerRegisterModel.setEmail(customerRegisterEntity.getEmail());
        customerRegisterModel.setMobileNo(customerRegisterEntity.getMobileNo());
        customerRegisterModel.setPassword(customerRegisterEntity.getPassword());

        List<CustomerAddressModel> addressModelList = new ArrayList<>();
        customerRegisterEntity.getCustomerAddressEntityList().forEach(registerEntity->{
            CustomerAddressModel addressModel = new CustomerAddressModel();
            addressModel.setHouseNo(registerEntity.getHouseNo());
            addressModel.setLandMark(registerEntity.getLandMark());
            addressModel.setPinCode(registerEntity.getPinCode());
            addressModel.setState(registerEntity.getState());
            addressModel.setCity(registerEntity.getCity());
            addressModel.setShippingAddress(registerEntity.getShippingAddress());
            addressModel.setBillingAddress(registerEntity.getBillingAddress());
            customerRegisterModel.setCustomerAddressModelList(addressModelList);
            addressModelList.add(addressModel);
        });

        return  customerRegisterModel;
    }

    //This getCustomers method will give all customers' information//
    public List<CustomerRegisterModel> getCustomers() {
        List<CustomerRegisterEntity> customDetails = customerRepository.findAll();
        return customDetails.stream().map(customer ->getAllData(customer)).collect(Collectors.toList());
    }
    //This method will check if the email is present then it will return all the information of that customer//
    public CustomerRegisterModel getCustomerByEmail(String email) {
        Optional<CustomerRegisterEntity> customerDetails = customerRepository.findById(email);
        if (customerDetails.isPresent()) {
            return getAllData(customerDetails.get());
        }
        return null;
    }

    //This method will check if the customer is not present
    // then it will throw a message "Invalid Details/please to register before login" and
    // it will Respond with 401 status code if user credentials are wrong.
    //By using Jasypt (Java Simplified Encryption and decryption) here encrypting and decrypting the password//
    public ResponseEntity<String> login(LoginModel loginModel) {
        String email = loginModel.getEmail();
        String password = loginModel.getPassword();
        CustomerRegisterEntity customerEntity = customerRepository.findByEmail(email);
        if(customerEntity!=null){
            PooledPBEStringEncryptor decrypt = new PooledPBEStringEncryptor();
            SimpleStringPBEConfig config = new SimpleStringPBEConfig();
            config.setPassword("decrypt_password"); // encryptor's private key
            config.setAlgorithm("PBEWithMD5AndDES");
            config.setKeyObtentionIterations("1000");
            config.setPoolSize("1");
            config.setProviderName("SunJCE");
            config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
            config.setStringOutputType("base64");
            decrypt.setConfig(config);
            System.out.println("decrypt"+decrypt);
            //here the password will be encrypted//
            String decrypted =  encryptDecryptPasswordService.decryptPassword(customerEntity.getPassword());
            System.out.println("decrypted-pwd"+decrypted);
            long EXPIRATIONTIME = 1000 * 60 * 60;
            System.out.println("expire-date"+EXPIRATIONTIME);
            String secret = "CJSSTECHNOLOGIES";
            //once the customer registered.
            //when a customer tries to logIn it will take encryptor's private key then
            // if the encrypted and decrypted passwords are equal then the customer can log in successfully
            //and also for authentication purpose here generating a jwt(JSON web token)and that token will expire after some time//
            if(email.equals(customerEntity.getEmail()) && password.equals(decrypted)){
                String JWT = Jwts.builder()
                        .setSubject(email)
                        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                        .signWith(SignatureAlgorithm.HS512, secret)
                        .compact();

                Date expirationDate= Jwts.parser()
                        .setSigningKey(secret)
                        .parseClaimsJws(JWT)
                        .getBody()
                        .getExpiration();
                System.out.println(expirationDate);
                return ResponseEntity.status(HttpStatus.CREATED).body("Jwt token :"+JWT);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Details/please to register before login");
    }
}

