package com.CategoryService.Interface;

import com.CategoryService.entity.Category;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface CategoryInterfase {

    ResponseEntity<String> createCategory(Category category);
    ResponseEntity<?> getByCategory(int id);
    ResponseEntity<List<Category>> getAllCategory();

}
