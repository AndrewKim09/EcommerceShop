package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemDAO {

    List<Item> getAllItems();
    List<Item> getItems();
    Item saveItem(Item item);

    void removeItem(int id);

    void subtractAmount(int quantity, int itemID);

    Optional<Item> getItem(int id);

}
