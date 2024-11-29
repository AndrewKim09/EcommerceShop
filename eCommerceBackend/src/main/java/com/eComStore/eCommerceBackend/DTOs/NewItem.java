package com.eComStore.eCommerceBackend.DTOs;

import lombok.Data;

@Data
public class NewItem {
    private String category;
    private String description;
    private String name;
    private String brand;
    private int price;
    private int quantity;
}
