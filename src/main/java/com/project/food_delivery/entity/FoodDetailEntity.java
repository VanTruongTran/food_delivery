package com.project.food_delivery.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "food_detail")
public class FoodDetailEntity implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "id_food")
    private FoodEntity food;

    @Column(name = "description")
    private String description;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "rating")
    private Float rating;

    /* CONSTRUCTORS */
    public FoodDetailEntity() {

    }

    /* GETTERS & SETTERS */
    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
}
