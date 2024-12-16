package com.eComStore.eCommerceBackend.Controllers;

import com.eComStore.eCommerceBackend.DTOs.RegisterRequest;
import com.eComStore.eCommerceBackend.DTOs.UserLogin;
import com.eComStore.eCommerceBackend.Models.Address;
import com.eComStore.eCommerceBackend.Models.Customer;
import com.eComStore.eCommerceBackend.Models.Item;
import com.eComStore.eCommerceBackend.Models.billingInfo;
import com.eComStore.eCommerceBackend.Services.AddressService;
import com.eComStore.eCommerceBackend.Services.CustomerService;
import com.eComStore.eCommerceBackend.Services.billingInfoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private billingInfoService billingService;

    @GetMapping("")
    public ResponseEntity<List<Customer>> getAll(){
        try{
            return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id){
        try{
            Optional<Customer> customer = customerService.getCustomerById(id);
            if(customer.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @PostMapping("")
    public ResponseEntity<Customer> customerLogin(@RequestBody UserLogin userLogin){
        Optional<Customer> checkUsername = customerService.getCustomerByUsername(userLogin.getUsername());
        if(checkUsername.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            try{
                Customer foundCustomer = checkUsername.get();

                if(foundCustomer.getPassword().equals(userLogin.getPassword())) return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
                else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> save(@RequestBody Customer customerToSave){
        try {
            // Check if the username already exists
            Optional<Customer> customer = customerService.getCustomerByUsername(customerToSave.getUsername());
            if (customer.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Customer newCustomer = customerService.save(customerToSave);
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> register(@RequestBody RegisterRequest request) {
        try {
            // Check if the username already exists
            Optional<Customer> customer = customerService.getCustomerByUsername(request.getUsername());
            if (customer.isPresent()) {
                return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 Conflict
            }

            Address newAddress = addressService.add(request);
            Customer newCustomer = customerService.register(request, newAddress.getId());
            System.out.println(request.getCvv());
            billingInfo newBillingInfo = new billingInfo(request.getCardNumber(), request.getExpirationDate(), request.getCvv(), newCustomer.getId());
            billingService.save(newBillingInfo);

            //201 Created
            return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
        } catch (Exception e) {
            //500 Internal Server Error for unexpected exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
