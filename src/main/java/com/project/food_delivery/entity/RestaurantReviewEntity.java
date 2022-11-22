package com.project.food_delivery.entity;

import javax.persistence.*;

@Entity
@Table(name = "restaurant_review")
public class RestaurantReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity restaurant;

    @Column(name = "content")
    private String content;

    @Column(name = "rate")
    private Float rate;

    /* CONSTRUCTORS */
    public RestaurantReviewEntity() {

    }

    /* GETTERS & SETTERS */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}
