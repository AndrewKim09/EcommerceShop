package com.eComStore.eCommerceBackend.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="po")
@Table(name="po")
@Getter
@NoArgsConstructor
public class Po {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int customerID;
    private String dateOfPurchase;
    private String itemID;

    public Po(int customerID, String dateOfPurchase, String itemID) {
        this.id = 0;
        this.customerID = customerID;
        this.dateOfPurchase = dateOfPurchase;
        this.itemID = itemID;
    }

    public int getId() {
        return id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public String getItemID() {
        return itemID;
    }
}
