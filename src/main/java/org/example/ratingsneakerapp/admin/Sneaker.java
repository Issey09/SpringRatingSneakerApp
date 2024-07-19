package org.example.ratingsneakerapp.admin;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sneakers")
public class Sneaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String brand;
    private String model;
    private String color;
    private int price;
    @Column(length = 1024)
    private String photoUrl;
}
