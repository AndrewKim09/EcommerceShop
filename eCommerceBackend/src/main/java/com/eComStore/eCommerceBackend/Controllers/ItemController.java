package com.eComStore.eCommerceBackend.Controllers;

import com.eComStore.eCommerceBackend.DTOs.NewItem;
import com.eComStore.eCommerceBackend.Models.Item;
import com.eComStore.eCommerceBackend.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody NewItem newItem){
        try {
            Item createdItem = itemService.add(newItem);
            return new ResponseEntity<>(createdItem,HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<Item> saveItem(@RequestBody Item item){
        try {
            Item newItem = itemService.save(item);
            return new ResponseEntity<>(newItem, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
