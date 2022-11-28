package com.project.food_delivery.service;

import com.project.food_delivery.entity.UserEntity;

public interface LoginService {
    public abstract UserEntity getUserByEmail(String email);
}
