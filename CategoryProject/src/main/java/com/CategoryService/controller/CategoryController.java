package com.CategoryService.controller;

import com.CategoryService.Interface.CategoryInterfase;
import com.CategoryService.entity.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category")

@Tag(name = "Category Service", description = "Operation related to Category Service")
public class CategoryController {

    @Autowired
    private CategoryInterfase categoryInterfase;

    @Operation(summary = "Create new Category ",description ="Add a new Category" )
    @PostMapping("")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        return categoryInterfase.createCategory(category);
    }
    @Operation(summary = "Get Category by Id",description ="Retrieve Details Of Specific Category Products " )
    @GetMapping("/{id}")
    public ResponseEntity<?> getByCatrgory(@PathVariable int id) {
        return categoryInterfase.getByCategory(id);
    }

    @Operation(summary = "Get All Category ",description ="Retrieve All Category Details" )
    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        return categoryInterfase.getAllCategory();
    }
}
