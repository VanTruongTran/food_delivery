package com.project.food_delivery.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<RestaurantReviewEntity> restaurantReviewsList;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<FoodEntity> foodsList;

    /* CONSTRUCTORS */
    public RestaurantEntity() {
        this.restaurantReviewsList = new ArrayList<RestaurantReviewEntity>();
        this.foodsList = new ArrayList<FoodEntity>();
    }

    /* GETTERS & SETTERS */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RestaurantReviewEntity> getRestaurantReviewsList() {
        return restaurantReviewsList;
    }

    public void setRestaurantReviewsList(List<RestaurantReviewEntity> restaurantReviewsList) {
        this.restaurantReviewsList = restaurantReviewsList;
    }

    public List<FoodEntity> getFoodsList() {
        return foodsList;
    }

    public void setFoodsList(List<FoodEntity> foodsList) {
        this.foodsList = foodsList;
    }
}
