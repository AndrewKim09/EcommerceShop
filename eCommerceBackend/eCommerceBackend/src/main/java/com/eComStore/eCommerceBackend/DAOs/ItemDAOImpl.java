package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Customer;
import com.eComStore.eCommerceBackend.Models.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.parser.Entity;
import java.util.List;

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


}
