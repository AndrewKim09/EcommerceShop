package com.eComStore.eCommerceBackend.Services;

import com.eComStore.eCommerceBackend.DAOs.ItemDAOImpl;
import com.eComStore.eCommerceBackend.DTOs.NewItem;
import com.eComStore.eCommerceBackend.Models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemDAOImpl itemDAO;


    public Item add(NewItem newItem){
        Item createItem = new Item(newItem.getCategory(), newItem.getDescription(), newItem.getName(), newItem.getBrand(), newItem.getPrice(), newItem.getQuantity());
        return itemDAO.saveItem(createItem);
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


}
