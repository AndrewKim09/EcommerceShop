package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Item;

import java.util.List;

public interface ItemDAO {
    List<Item> getItems();
    Item saveItem(Item item);

    void removeItem(int id);

}
