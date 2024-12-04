package com.eComStore.eCommerceBackend.Controllers;

import com.eComStore.eCommerceBackend.Models.billingInfo;
import com.eComStore.eCommerceBackend.Services.billingInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/billing")
public class billingInfoController {
    @Autowired
    billingInfoService billingService;

    @GetMapping("")
    public ResponseEntity<billingInfo> getCustomerBillingInfo(@RequestParam int id){
        try{
            Optional<billingInfo> foundBilling = billingService.get(id);

            if(foundBilling.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            else return new ResponseEntity<>(foundBilling.get(),HttpStatus.OK);

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public billingInfo save(@RequestBody billingInfo billinginfo){
        return billingService.save(billinginfo);
    }


}
