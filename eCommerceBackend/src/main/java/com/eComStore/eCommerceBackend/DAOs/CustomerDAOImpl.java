package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        TypedQuery<Customer> query = entityManager.createQuery(
                "SELECT c FROM customer c WHERE c.username = :username", Customer.class);
        query.setParameter("username", username);

        List<Customer> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
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
