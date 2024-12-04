package com.eComStore.eCommerceBackend.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name ="billinginfo")
@Table(name = "billinginfo")
@Getter
@NoArgsConstructor
public class billingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int customerID;
    private String cardNumber;
    private String expirationDate;
    @Column(name = "CVV")
    private String cvv;

    public billingInfo(String cardNumber, String expirationDate, String cvv, int customerID) {
        this.id = 0;
        this.customerID = customerID;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }
}
