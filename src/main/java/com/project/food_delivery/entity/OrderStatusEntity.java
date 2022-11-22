package com.project.food_delivery.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_status")
public class OrderStatusEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_status")
    private StatusEntity status;

    /* CONSTRUCTORS */
    public OrderStatusEntity() {

    }

    /* GETTERS & SETTERS */
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
