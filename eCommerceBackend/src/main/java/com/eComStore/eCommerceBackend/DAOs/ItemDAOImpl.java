package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class ItemDAOImpl implements ItemDAO {
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Item> getItems() {
        return entityManager.createQuery("from item", Item.class).getResultList();
    }

    @Override
    public Optional<Item> getItem(int id) {
        return entityManager.createQuery("SELECT i FROM item i WHERE i.id = :itemID", Item.class)
                .setParameter("itemID", id)
                .getResultStream()
                .findFirst();
    }


    @Override
    public void addItemImage(MultipartFile image, int id) throws IOException {
        Item item = entityManager.createQuery("SELECT i FROM item i WHERE i.id = :itemID", Item.class)
                .setParameter("itemID", id)
                .getResultStream()
                .findFirst()
                .get();
        item.setImage(image.getBytes());

        entityManager.merge(item);
        entityManager.flush();


    }

    @Override
    public List<Item> getAllItems(){
        try{
            return entityManager.createQuery("SELECT i FROM item i", Item.class).getResultList();
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    @Override
    public Item saveItem(Item item) {
        try{
            if (item.getItemID() == 0) {
                entityManager.persist(item);
                entityManager.flush();
                System.out.println(item);
                return item;
            }
            entityManager.merge(item);
            entityManager.flush();
            return item;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
    @Override
    public void removeItem(int id) {
        Item item = entityManager.find(Item.class, id);
        if (item != null) {
            entityManager.remove(item);
        }
    }

    @Override
    public void subtractAmount(int quantity, int itemID) {
        try {
            Item item = entityManager.find(Item.class, itemID);

            if (item == null) {
                System.out.println("Item not found with ID: " + itemID);
                return;
            }

            // Check if the current stock is sufficient
            int currentStock = item.getQuantity();
            if (currentStock < quantity) {
                System.out.println("Insufficient stock for item ID: " + itemID);
                return;
            }

            item.setQuantity(currentStock - quantity);

            // Update the item in the database
            entityManager.merge(item);
            entityManager.flush();

            System.out.println("Stock updated successfully for item ID: " + itemID);
        } catch (Exception e) {
            System.out.println("Error occurred while updating stock: " + e.getMessage());
        }
    }



}
