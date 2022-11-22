package com.project.food_delivery.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "status")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
    private List<OrderStatusEntity> orderStatusesList;

    /* CONSTRUCTORS */
    public StatusEntity() {
        this.orderStatusesList = new ArrayList<OrderStatusEntity>();
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

    public List<OrderStatusEntity> getOrderStatusesList() {
        return orderStatusesList;
    }

    public void setOrderStatusesList(List<OrderStatusEntity> orderStatusesList) {
        this.orderStatusesList = orderStatusesList;
    }
}
