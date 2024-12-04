package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Address;
import com.eComStore.eCommerceBackend.Models.billingInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class billingInfoDAOImpl implements billingInfoDAO {

    @PersistenceContext
    EntityManager entityManager;
    @Override
    public billingInfo save(billingInfo billinginfo) {
        try
        {
            if (billinginfo.getId() == 0) {
                entityManager.persist(billinginfo);
                return billinginfo;
            }
            entityManager.merge(billinginfo);
            return billinginfo;
        } catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    @Override
    public Optional<billingInfo> get(int id) {
        TypedQuery<billingInfo> query = entityManager.createQuery(
                "SELECT a FROM billinginfo a WHERE a.id = :id", billingInfo.class);
        query.setParameter("id", id);

        List<billingInfo> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }
}
