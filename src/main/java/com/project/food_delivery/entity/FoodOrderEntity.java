package com.project.food_delivery.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "food_order")
public class FoodOrderEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_food")
    private FoodEntity food;

    @Column(name = "price")
    private Float price;

    @Column(name = "quality")
    private Integer quality;

    /* CONSTRUCTORS */
    public FoodOrderEntity() {

    }

    /* GETTERS & SETTERS */
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuality() {
        return quality;
    }

    public void setQuality(Integer quality) {
        this.quality = quality;
    }
}
