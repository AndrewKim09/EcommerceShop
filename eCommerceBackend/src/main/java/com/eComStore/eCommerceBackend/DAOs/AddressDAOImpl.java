package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Address;
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
public class AddressDAOImpl implements AddressDAO{
    @PersistenceContext
    EntityManager entityManager;


    @Override
    public Optional<Address> getById(int id) {
        TypedQuery<Address> query = entityManager.createQuery(
                "SELECT a FROM address a WHERE a.id = :id", Address.class);
        query.setParameter("id", id);

        List<Address> resultList = query.getResultList();
        return resultList.isEmpty() ? Optional.empty() : Optional.of(resultList.get(0));
    }
    @Override
    public Address save(Address address) {
        try {
            if (address.getId() == 0) {
                entityManager.persist(address);
                entityManager.flush();
                System.out.println(address.getId());

                return address;
            }
            entityManager.merge(address);
            return address;
        }
        catch (Exception e){
            System.out.println(e);
            return null;
        }
    }
}
