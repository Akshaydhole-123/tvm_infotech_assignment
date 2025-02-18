package com.CategoryService.Interface;

import com.CategoryService.entity.Category;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

public interface CategoryInterfase {

    ResponseEntity<String> createCategory(Category category);
    ResponseEntity<?> getByCategory(int id);
    ResponseEntity<?> getAllCategory();

}
