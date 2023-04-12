package com.pascadenis.honestee.entities;

import javax.persistence.*;

@Entity
public class Products {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    private String name;

    private String color;

    private double price;

    @Lob
    private String imageSrc;

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}
