package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.billingInfo;

import java.util.Optional;

public interface billingInfoDAO {

    public Optional<billingInfo> get(int customerID);

    public billingInfo save(billingInfo billinginfo);


}
