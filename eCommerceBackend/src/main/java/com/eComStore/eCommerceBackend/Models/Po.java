package com.eComStore.eCommerceBackend.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name="po")
@Table(name="po")
@NoArgsConstructor
public class Po {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int customerID;
    private String dateOfPurchase;
    private int itemID;

    public Po(int customerID, String dateOfPurchase, int itemID) {
        this.id = 0;
        this.customerID = customerID;
        this.dateOfPurchase = dateOfPurchase;
        this.itemID = itemID;
    }

}
