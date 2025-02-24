package com.CategoryService;


import com.CategoryService.Interface.CategoryInterfase;
import com.CategoryService.controller.CategoryController;
import com.CategoryService.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryInterfase categoryInterfase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @Order(1)
    public void test_getAllCategory() {

        List<Category> categories = Arrays.asList(new Category(202, "Pass"), new Category(203, "Bike"));
        when(categoryInterfase.getAllCategory()).thenReturn(new ResponseEntity(categories, HttpStatus.OK));
        ResponseEntity<?> response = categoryController.getAllCategory();
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(categories, response.getBody());
    }

    @Test
    @Order(2)
    public void test_createCategory() {

        Category categories = new Category(205, "Pass");
        when(categoryInterfase.createCategory(categories)).thenReturn(new ResponseEntity<>("Category Created Successfully", HttpStatus.OK));
        ResponseEntity<String> response = categoryController.createCategory(categories);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Category Created Successfully", response.getBody());

    }

    @Test
    @Order(3)
    public void test_getByProduct() {

        Category category = new Category(202, "Pass");
        when(categoryInterfase.getByCategory(202)).thenReturn(new ResponseEntity(category, HttpStatus.OK));
        ResponseEntity<?> response = categoryController.getByCatrgory(202);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(category, response.getBody());
    }
}
