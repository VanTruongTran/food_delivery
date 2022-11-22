package com.project.food_delivery.entity;

import javax.persistence.*;

@Entity
@Table(name = "food_addon")
public class FoodAddonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "id_food")
    private FoodEntity food;

    /* CONSTRUCTORS */
    public FoodAddonEntity() {

    }

    /* GETTERS & SETTERS */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }
}
