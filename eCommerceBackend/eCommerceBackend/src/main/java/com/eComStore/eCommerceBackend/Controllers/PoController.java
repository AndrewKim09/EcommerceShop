package com.eComStore.eCommerceBackend.Controllers;

import com.eComStore.eCommerceBackend.DTOs.IdRequest;
import com.eComStore.eCommerceBackend.DTOs.OrderRequest;
import com.eComStore.eCommerceBackend.Models.Po;
import com.eComStore.eCommerceBackend.Services.PoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class PoController {

    @Autowired
    private PoService poService;

    @PostMapping("")
    public ResponseEntity<Po> order(@RequestBody OrderRequest orderRequest){
        try{
            Po placedOrder = poService.order(orderRequest);
            return new ResponseEntity<>(placedOrder, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<Po>> getUsernameOrders(@RequestBody IdRequest idRequest){
        try{
            List<Po> usernameOrders = poService.getUserOrders(idRequest.getId());
            return new ResponseEntity<>(usernameOrders,HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Po>> getAllOrders(){
        try{
            List<Po> allOrders = poService.getAllOrders();
            return new ResponseEntity<>(allOrders, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
