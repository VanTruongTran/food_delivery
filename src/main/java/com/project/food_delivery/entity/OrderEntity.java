package com.project.food_delivery.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @Column(name = "estimate_ship")
    private String estimateShip;

    @Column(name = "deliver_address")
    private String deliverAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderStatusEntity> orderStatusesList;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<FoodOrderEntity> foodOrdersList;

    /* CONSTRUCTORS */
    public OrderEntity() {
        this.orderStatusesList = new ArrayList<OrderStatusEntity>();
        this.foodOrdersList = new ArrayList<FoodOrderEntity>();
    }

    /* GETTERS & SETTERS */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getEstimateShip() {
        return estimateShip;
    }

    public void setEstimateShip(String estimateShip) {
        this.estimateShip = estimateShip;
    }

    public String getDeliverAddress() {
        return deliverAddress;
    }

    public void setDeliverAddress(String deliverAddress) {
        this.deliverAddress = deliverAddress;
    }

    public List<OrderStatusEntity> getOrderStatusesList() {
        return orderStatusesList;
    }

    public void setOrderStatusesList(List<OrderStatusEntity> orderStatusesList) {
        this.orderStatusesList = orderStatusesList;
    }

    public List<FoodOrderEntity> getFoodOrdersList() {
        return foodOrdersList;
    }

    public void setFoodOrdersList(List<FoodOrderEntity> foodOrdersList) {
        this.foodOrdersList = foodOrdersList;
    }
}
