package com.CategoryService.controller;

import com.CategoryService.Interfase.CategoryInterfase;

import com.CategoryService.entity.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryInterfase categoryInterfaseinterfase;



    @PostMapping("")
    public ResponseEntity<String> createCategory(@RequestBody Category category){

        return categoryInterfaseinterfase.createCategory(category);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getByCatrgory(@PathVariable int id){

        return categoryInterfaseinterfase.getByCategory(id);
    }
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory(){

        return  categoryInterfaseinterfase.getAllCategory();
    }


}
