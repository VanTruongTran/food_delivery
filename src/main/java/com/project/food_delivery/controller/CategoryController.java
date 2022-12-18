package com.project.food_delivery.controller;

import com.project.food_delivery.entity.CategoryEntity;
import com.project.food_delivery.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/top6")
    public ResponseEntity<?> getExplorerCategory() {
        List<CategoryEntity> categoriesList = categoryService.getExplorerCategory();

        return new ResponseEntity<>(categoriesList, HttpStatus.OK);
    }
}
