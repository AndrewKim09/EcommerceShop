package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Item;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ItemDAO {

    void addItemImage(MultipartFile image, int id) throws IOException;

    List<Item> getAllItems();
    List<Item> getItems();
    Item saveItem(Item item);

    void removeItem(int id);

    void subtractAmount(int quantity, int itemID);

    Optional<Item> getItem(int id);

}
