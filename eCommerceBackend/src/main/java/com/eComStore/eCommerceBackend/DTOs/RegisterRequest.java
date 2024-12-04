package com.eComStore.eCommerceBackend.DTOs;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String country;
    private String zip;
    private String phone;
    private String street;
    private String province;
    private String firstName;
    private String lastName;
    private String cardNumber;
    private String expirationDate;
    private String cvv;
}
