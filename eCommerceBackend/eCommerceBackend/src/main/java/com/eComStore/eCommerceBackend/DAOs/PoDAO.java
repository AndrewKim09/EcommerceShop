package com.eComStore.eCommerceBackend.DAOs;

import com.eComStore.eCommerceBackend.Models.Po;

import java.util.List;

public interface PoDAO {
    List<Po> getAllPo();
    List<Po> getUsernameOrders(int customerId);
    Po addOrder(Po order);
}
