package com.project.food_delivery.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "material")
public class MaterialEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL)
    private List<FoodMaterialEntity> foodMaterialsList;

    /* CONSTRUCTORS */
    public MaterialEntity() {
        this.foodMaterialsList = new ArrayList<FoodMaterialEntity>();
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

    public List<FoodMaterialEntity> getFoodMaterialsList() {
        return foodMaterialsList;
    }

    public void setFoodMaterialsList(List<FoodMaterialEntity> foodMaterialsList) {
        this.foodMaterialsList = foodMaterialsList;
    }
}
