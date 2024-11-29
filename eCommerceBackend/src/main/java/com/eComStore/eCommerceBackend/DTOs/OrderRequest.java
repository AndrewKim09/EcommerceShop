package com.eComStore.eCommerceBackend.DTOs;

import lombok.Data;

@Data
public class OrderRequest {
    private int customerID;
    private String dateOfPurchase;
    private String itemID;
}