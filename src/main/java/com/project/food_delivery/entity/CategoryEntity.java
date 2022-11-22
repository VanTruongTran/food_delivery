package com.project.food_delivery.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image")
    private String image;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<FoodEntity> foodsList;

    /* CONSTRUCTORS */
    public CategoryEntity() {
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

    public List<FoodEntity> getFoodsList() {
        return foodsList;
    }

    public void setFoodsList(List<FoodEntity> foodsList) {
        this.foodsList = foodsList;
    }
}
