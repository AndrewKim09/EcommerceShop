package com.eComStore.eCommerceBackend.Controllers;

import com.eComStore.eCommerceBackend.DTOs.IdRequest;
import com.eComStore.eCommerceBackend.Models.Address;
import com.eComStore.eCommerceBackend.Services.AddressService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping("")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address){
        try {
            Address savedAddress = addressService.save(address);
            return new ResponseEntity<>(savedAddress, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("")
    public ResponseEntity<Address> getAddress(@RequestParam int id){
        try {
            System.out.println(id);
            Optional<Address> foundAddress = addressService.getAddress(id);
            if(foundAddress.isEmpty()) return new ResponseEntity(HttpStatus.NOT_FOUND);
            else {
                return new ResponseEntity(foundAddress.get(), HttpStatus.OK);
            }
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
