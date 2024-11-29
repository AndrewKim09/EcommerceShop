package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Po;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Transactional
@Repository
public class PoDAOImpl implements PoDAO{
    @PersistenceContext
    EntityManager entityManager;
    @Override
    public List<Po> getAllPo() {
        return entityManager.createQuery("from po", Po.class).getResultList();
    }

    @Override
    public List<Po> getUsernameOrders(int customerId) {
        TypedQuery<Po> query = entityManager.createQuery(
                "SELECT p FROM Po p WHERE p.customerId = :customerId", Po.class);
        query.setParameter("customerId", customerId);

        List<Po> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Po addOrder(Po order){
        try {
            entityManager.persist(order);
            return order;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
