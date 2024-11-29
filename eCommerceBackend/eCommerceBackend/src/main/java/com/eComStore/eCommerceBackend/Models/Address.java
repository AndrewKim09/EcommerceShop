package com.eComStore.eCommerceBackend.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "address")
@Table(name = "address")
@Getter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private String phone;
    private String province;
    private String street;
    private String zip;

    public Address(String country, String phone, String province, String street, String zip) {
        this.id = 0;
        this.country = country;
        this.phone = phone;
        this.province = province;
        this.street = street;
        this.zip = zip;
    }

}
