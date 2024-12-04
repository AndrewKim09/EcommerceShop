package com.eComStore.eCommerceBackend.Services;

import com.eComStore.eCommerceBackend.DAOs.billingInfoDAOImpl;
import com.eComStore.eCommerceBackend.Models.billingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class billingInfoService {
    @Autowired
    billingInfoDAOImpl billingDAO;

    public billingInfo save(billingInfo billinginfo){
        return billingDAO.save(billinginfo);
    }

    public Optional<billingInfo> get(int customerID){
        return billingDAO.get(customerID);
    }

}
