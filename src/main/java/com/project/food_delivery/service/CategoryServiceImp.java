package com.project.food_delivery.service;

import com.project.food_delivery.entity.CategoryEntity;
import com.project.food_delivery.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getExplorerCategory() {
        return categoryRepository.GetTop6Category();
    }
}
