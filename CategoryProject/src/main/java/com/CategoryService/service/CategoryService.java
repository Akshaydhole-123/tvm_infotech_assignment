package com.CategoryService.service;

import com.CategoryService.Interface.CategoryInterfase;


import com.CategoryService.Interface.ProductClient;
import com.CategoryService.entity.Category;

import com.CategoryService.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements CategoryInterfase {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    public ResponseEntity<String> createCategory(Category category) {
        try{
            categoryRepository.save(category);
            return ResponseEntity.status(HttpStatus.OK).body("Category Created Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while creating category");
        }
    }

    @Override
    public ResponseEntity<?> getByCategory(int id) {

        try {

            Category category = categoryRepository.findById(id).orElse(null);


            category.setProductList(productClient.getProductsByCategory(category.getId()));
            return ResponseEntity.status(HttpStatus.OK).body(category);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.OK).body("Category Not exists ");
        }
    }

    @Override
    public ResponseEntity<?> getAllCategory() {
        try {


            List<Category> categories = categoryRepository.findAll();

            List<Category> newCategory = categories.stream().map(category -> {
                category.setProductList(productClient.getProductsByCategory(category.getId()));
                return category;
            }).collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.OK).body(newCategory);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Category Does Not Exists ");
        }

      }

}
