package com.eComStore.eCommerceBackend.Controllers;

import com.eComStore.eCommerceBackend.DTOs.NewItem;
import com.eComStore.eCommerceBackend.Models.Item;
import com.eComStore.eCommerceBackend.Services.ItemService;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(){
        try {
            List<Item> allItems = itemService.getAll();
            return new ResponseEntity<>(allItems,HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable("id") int id){
        try{
            Optional<Item> item = itemService.getItem(id);
            if(item.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(item.get(), HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<Item> addItem(@ModelAttribute NewItem newItem , @RequestParam("image")    MultipartFile image) {
        try {
            System.out.println(image);

            Item createdItem = itemService.add(newItem, image);
            return new ResponseEntity<>(createdItem, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value ="/save")
    public ResponseEntity<Item> saveItem(@RequestBody Item item){
        try {
            Item newItem = itemService.save(item);
            return new ResponseEntity<>(newItem, HttpStatus.OK);
        } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
