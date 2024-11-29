package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Address;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface AddressDAO {

    Optional<Address> getById(int id);
    Address save(Address address);


}
