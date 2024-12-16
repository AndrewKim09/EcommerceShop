package com.eComStore.eCommerceBackend.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity(name="item")
@Table(name="item")
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemID;
    private String category;
    private String description;
    private String name;
    private String brand;
    private int price;
    private int quantity;
    private byte[] image;

    public Item(String category, String description, String name, String brand, int price, int quantity, byte[] image) {
        this.itemID = 0;
        this.category = category;
        this.description = description;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.brand = brand;
        this.image = image;
    }
}
