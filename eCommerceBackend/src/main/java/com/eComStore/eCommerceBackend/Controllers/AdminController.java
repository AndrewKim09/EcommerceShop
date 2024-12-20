package com.eComStore.eCommerceBackend.Controllers;

import com.eComStore.eCommerceBackend.DTOs.UserLogin;
import com.eComStore.eCommerceBackend.Models.Address;
import com.eComStore.eCommerceBackend.Models.Admin;
import com.eComStore.eCommerceBackend.Models.Customer;
import com.eComStore.eCommerceBackend.Models.Item;
import com.eComStore.eCommerceBackend.Services.AddressService;
import com.eComStore.eCommerceBackend.Services.AdminService;
import com.eComStore.eCommerceBackend.Services.CustomerService;
import com.eComStore.eCommerceBackend.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ItemService itemService;

    @PostMapping("")
    public ResponseEntity<Admin> adminLogin(@RequestBody UserLogin userLogin){
        Optional<Admin> checkUsername = adminService.getAdminByUsername(userLogin.getUsername());
        if(checkUsername.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else{
            try{
                Admin foundAdmin = checkUsername.get();

                if(foundAdmin.getPassword().equals(userLogin.getPassword())) return new ResponseEntity<>(foundAdmin, HttpStatus.OK);
                else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } catch (Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping(value="/addImage/{id}", consumes = "multipart/form-data")
    public ResponseEntity addImage(@RequestParam("image") MultipartFile image, @PathVariable("id") int id){
        try{
            itemService.addImage(image, id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


//    @PostMapping("/login")
//    public ResponseEntity<Customer> save(@RequestParam String username, @RequestParam String password){
//        try
//        {
//            Optional<Admin> admin = adminService.getAdminByUsername(username);
//
//            if (admin.isPresent()) return new ResponseEntity<>(HttpStatus.CONFLICT);
//
//            Customer newCustomer = new Customer(newAddress.getId(), username, password, firstName, lastName);
//
//            return new ResponseEntity<>(newCustomer, HttpStatus.OK);
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
}
