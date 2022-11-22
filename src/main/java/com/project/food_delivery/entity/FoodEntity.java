package com.project.food_delivery.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "food")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity restaurant;

    @OneToOne(mappedBy = "food", cascade = CascadeType.ALL)
    private FoodDetailEntity foodDetail;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodReviewEntity> foodReviewsList;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodAddonEntity> foodAddonsList;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodMaterialEntity> foodMaterialsList;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    private List<FoodOrderEntity> foodOrdersList;

    /* CONSTRUCTORS */
    public FoodEntity() {
        this.foodReviewsList = new ArrayList<FoodReviewEntity>();
        this.foodAddonsList = new ArrayList<FoodAddonEntity>();
        this.foodMaterialsList = new ArrayList<FoodMaterialEntity>();
        this.foodOrdersList = new ArrayList<FoodOrderEntity>();
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public FoodDetailEntity getFoodDetail() {
        return foodDetail;
    }

    public void setFoodDetail(FoodDetailEntity foodDetail) {
        this.foodDetail = foodDetail;
    }

    public List<FoodReviewEntity> getFoodReviewsList() {
        return foodReviewsList;
    }

    public void setFoodReviewsList(List<FoodReviewEntity> foodReviewsList) {
        this.foodReviewsList = foodReviewsList;
    }

    public List<FoodAddonEntity> getFoodAddonsList() {
        return foodAddonsList;
    }

    public void setFoodAddonsList(List<FoodAddonEntity> foodAddonsList) {
        this.foodAddonsList = foodAddonsList;
    }

    public List<FoodMaterialEntity> getFoodMaterialsList() {
        return foodMaterialsList;
    }

    public void setFoodMaterialsList(List<FoodMaterialEntity> foodMaterialsList) {
        this.foodMaterialsList = foodMaterialsList;
    }

    public List<FoodOrderEntity> getFoodOrdersList() {
        return foodOrdersList;
    }

    public void setFoodOrdersList(List<FoodOrderEntity> foodOrdersList) {
        this.foodOrdersList = foodOrdersList;
    }
}
