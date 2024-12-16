package com.eComStore.eCommerceBackend.Services;

import com.eComStore.eCommerceBackend.DAOs.ItemDAOImpl;
import com.eComStore.eCommerceBackend.DTOs.NewItem;
import com.eComStore.eCommerceBackend.Models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemDAOImpl itemDAO;


    public Item add(NewItem newItem, MultipartFile image) throws IOException {
        Item createdItem = new Item(newItem.getCategory(), newItem.getDescription(), newItem.getName(), newItem.getBrand(), newItem.getPrice(), newItem.getQuantity(), image.getBytes());
        return itemDAO.saveItem(createdItem);
    }

    public List<Item> getAll() {
        return itemDAO.getAllItems();
    }

    public Item save(Item item){
        return itemDAO.saveItem(item);
    }

    public void subtractAmount(int quantity, int itemID){
        itemDAO.subtractAmount(quantity, itemID);
    }


    public Optional<Item> getItem(int id) {
        return itemDAO.getItem(id);
    }
}
