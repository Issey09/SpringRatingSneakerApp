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

    @Column(name = "likes", nullable = false)
    private int likes = 0;

    @Column(name = "dislikes", nullable = false)
    private int dislikes = 0;

    @Column(nullable = false)
    private int rating = 0;

    private int price;

    @Column(length = 1024)
    private String photoUrl;

    @PrePersist
    @PreUpdate
    private void calculateRating() {
        this.rating = this.likes - this.dislikes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
        calculateRating();
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
        calculateRating();
    }

    public int getRating() {
        return rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
