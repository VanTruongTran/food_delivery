package com.project.food_delivery.service;

import com.project.food_delivery.entity.UserEntity;
import com.project.food_delivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean checkLogin(String email, String password) {
        List<UserEntity> userEntityList = userRepository.findByEmailAndPassword(email, password);
        return !userEntityList.isEmpty();
    }
}
