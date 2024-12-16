package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Customer;
import com.eComStore.eCommerceBackend.Models.Item;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    List<Customer> getAll();
    Optional<Customer> findByUsername(String username);
    Customer save(Customer customer);

    Optional<Customer> findById(int id);
}
