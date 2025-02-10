package com.CategoryService.controller;

import com.CategoryService.Interface.CategoryInterfase;
import com.CategoryService.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryInterfase categoryInterfase;

    @PostMapping("")

    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        return categoryInterfase.createCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByCatrgory(@PathVariable int id) {
        return categoryInterfase.getByCategory(id);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        return categoryInterfase.getAllCategory();
    }
}
