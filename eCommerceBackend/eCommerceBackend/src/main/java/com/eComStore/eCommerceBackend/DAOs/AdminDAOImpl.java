package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Admin;
import com.eComStore.eCommerceBackend.Models.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminDAOImpl implements AdminDAO{
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Optional<Admin> findByUsername(String username) {
        TypedQuery<Admin> query = entityManager.createQuery(
                "SELECT c FROM admin c WHERE c.username = :username", Admin.class);
        query.setParameter("username", username);

        List<Admin> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }

    @Override
    public Admin save(Admin admin) {
        try
        {
            if (admin.getId() == 0) {
                entityManager.persist(admin);
                return admin;
            }
            entityManager.merge(admin);
            return admin;
        } catch (Exception e){
            return null;
        }
    }
}
