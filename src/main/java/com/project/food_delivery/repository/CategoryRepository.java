package com.project.food_delivery.repository;

import com.project.food_delivery.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    @Query(value = "SELECT * FROM category AS c LIMIT 6",nativeQuery = true)
    public abstract List<CategoryEntity> GetTop6Category();
}
