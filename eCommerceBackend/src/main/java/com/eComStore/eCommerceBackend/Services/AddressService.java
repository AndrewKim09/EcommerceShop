package com.eComStore.eCommerceBackend.Services;

import com.eComStore.eCommerceBackend.DAOs.AddressDAOImpl;
import com.eComStore.eCommerceBackend.DTOs.RegisterRequest;
import com.eComStore.eCommerceBackend.Models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressDAOImpl addressDAO;

    public Address add(RegisterRequest request){
        Address newAddress = new Address(
                request.getCountry(),
                request.getPhone(),
                request.getProvince(),
                request.getStreet(),
                request.getZip()
        );
        return addressDAO.save(newAddress);
    }

    public Address save(Address address){
        return addressDAO.save(address);
    }

    public Optional<Address> getAddress(int id) {
        return addressDAO.getById(id);
    }
}
