package com.eComStore.eCommerceBackend.Services;

import com.eComStore.eCommerceBackend.DAOs.CustomerDAOImpl;
import com.eComStore.eCommerceBackend.DTOs.RegisterRequest;
import com.eComStore.eCommerceBackend.Models.Customer;
import com.eComStore.eCommerceBackend.Models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerDAOImpl customerDAO;
    public Customer register(RegisterRequest request , int addressId){
        Customer newCustomer = new Customer(
                addressId,
                request.getUsername(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName()
        );

        return customerDAO.save(newCustomer);
    }

    public Customer save(Customer customer){
        return customerDAO.save(customer);
    }


    public Optional<Customer> getCustomerByUsername(String username){
        return customerDAO.findByUsername(username);
    }
    public List<Customer> getAll(){return customerDAO.getAll();}

    public Optional<Customer> getCustomerById(int id) {
        return customerDAO.findById(id);
    }
}
