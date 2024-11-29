package com.eComStore.eCommerceBackend.Services;

import com.eComStore.eCommerceBackend.DAOs.PoDAOImpl;
import com.eComStore.eCommerceBackend.DTOs.OrderRequest;
import com.eComStore.eCommerceBackend.Models.Po;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoService {

    @Autowired
    private PoDAOImpl poDAO;

    public Po order(OrderRequest orderRequest){
        Po order = new Po(orderRequest.getCustomerID(), orderRequest.getDateOfPurchase(), orderRequest.getItemID());
        return poDAO.addOrder(order);
    }

    public List<Po> getUserOrders(int id){
        return poDAO.getUsernameOrders(id);
    }

    public List<Po> getAllOrders(){
        return poDAO.getAllPo();
    }


}
