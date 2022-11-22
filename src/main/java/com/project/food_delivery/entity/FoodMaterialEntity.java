package com.project.food_delivery.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "food_material")
public class FoodMaterialEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_food")
    private FoodEntity food;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_material")
    private MaterialEntity material;

    /* CONSTRUCTORS */
    public FoodMaterialEntity() {

    }

    /* GETTERS & SETTERS */
    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public MaterialEntity getMaterial() {
        return material;
    }

    public void setMaterial(MaterialEntity material) {
        this.material = material;
    }
}
