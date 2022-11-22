package com.project.food_delivery.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_detail")
public class UserDetailEntity implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "id_user")
    private UserEntity user;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile_phone")
    private String mobilePhone;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    /* CONSTRUCTORS */
    public UserDetailEntity() {

    }

    /* GETTERS & SETTERS */
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
