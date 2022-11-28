package com.project.food_delivery.repository;

import com.project.food_delivery.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public abstract UserEntity findFirstByEmail(String email);
}
