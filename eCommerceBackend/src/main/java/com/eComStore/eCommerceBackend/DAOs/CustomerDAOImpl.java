package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Customer> getAll() {
        return entityManager.createQuery("from customer", Customer.class).getResultList();
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        return entityManager.createQuery(
                        "SELECT c FROM customer c WHERE c.username = :username", Customer.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Optional<Customer> findById(int id){
        return entityManager.createQuery("SELECT c FROM customer c WHERE c.id = :id", Customer.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }


    @Override
    public Customer save(Customer customer) {
        try{
            if (customer.getId() == 0) {
                entityManager.persist(customer);
                entityManager.flush();
                System.out.println(customer);
                return customer;
            }
            entityManager.merge(customer);
            entityManager.flush();
            return customer;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
