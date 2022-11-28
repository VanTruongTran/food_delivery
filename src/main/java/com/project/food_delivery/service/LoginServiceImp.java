package com.project.food_delivery.service;

import com.project.food_delivery.entity.UserEntity;
import com.project.food_delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity getUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }
}
