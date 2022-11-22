package com.project.food_delivery.entity;

import javax.persistence.*;

@Entity
@Table(name = "food_review")
public class FoodReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_food")
    private FoodEntity food;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @Column(name = "content")
    private String content;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "rate")
    private Float rate;

    /* CONSTRUCTORS */
    public FoodReviewEntity() {

    }

    /* GETTERS & SETTERS */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}
